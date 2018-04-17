package com.zheng.core.redis;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhengct on 2018/4/17
 */
public class RedisDao {


    public RedisDao() {
    }

    private Jedis getResource() {
        return MyJedisPool.getResource();
    }

    private Jedis getResource(Integer dbIdx) {
        return MyJedisPool.getResource(dbIdx);
    }

    private void returnResource(Jedis jedis) {
        jedis.close();
    }

    private void returnBrokenResource(Jedis jedis) {
        jedis.close();
    }

    /*
     * 对String操作的命令(全): 1、BITCOUNT 可用版本：>=2.6.0，不支持 ，比较有用 2、BITOP 可用版本：>=
     * 2.6.0，不支持 3、GETBIT 可用版本：>= 2.2.0 4、INCRBYFLOAT 可用版本：>= 2.6.0，不支持 5、PSETEX
     * 可用版本：>= 2.6.0，不支持
     *
     * 对List操作的命令(全): 1、BLPOP 2、BRPOP 3、BRPOPLPUSH
     *
     * 对Sorted Set操作的命令：1、ZRANGEBYSCORE 2、ZREVRANGEBYSCORE 3、ZUNIONSTORE
     * 4、ZINTERSTORE
     *
     * 对Hash操作的命令（全）： 1、HINCRBYFLOAT 可用版本：>= 2.6.0，不支持
     *
     * 对Key操作的命令 （全）: 1、DUMP 2、MIGRATE 3、OBJECT 4、PERSIST 5、PEXPIRE 6、PEXPIREAT
     * 7、PTTL 8、RESTORE
     *
     * 对Server操作的命令（后补）：
     */

    /*
     * ==============================对String的操作命令================================
     */

    /**
     * 将字符串值 value 关联到 key 。 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。 可用版本： >=
     * 1.0.0 时间复杂度： O(1) 返回值： 在 Redis 2.6.12 版本以前， SET 命令总是返回 OK 。 从 Redis
     * 2.6.12 版本开始， SET 在设置操作成功完成时，才返回 OK 因为 SET 命令可以通过参数来实现和 SETNX 、 SETEX 和
     * PSETEX 三个命令的效果，所以将来的 Redis 版本可能会废弃并最终移除 SETNX 、 SETEX 和 PSETEX 这三个命令。
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(key, value);
            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 同时设置一个或多个 key-value 对。 如果某个给定 key 已经存在，那么 MSET
     * 会用新值覆盖原来的旧值，如果这不是你所希望的效果，请考虑使用 MSETNX 命令：它只会在所有给定 key 都不存在的情况下进行设置操作。
     * MSET 是一个原子性(atomic)操作，所有给定 key 都会在同一时间内被设置，某些给定 key 被更新而另一些给定 key
     * 没有改变的情况，不可能发生。 可用版本：>= 1.0.1 时间复杂度：O(N)， N 为要设置的 key 数量。 返回值： 总是返回 OK (因为
     * MSET 不可能失败)
     */
    public void mset(String... keysvalues) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.mset(keysvalues);
            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 返回 key 所关联的字符串值。 如果 key 不存在那么返回特殊值 nil 。在jedis中为null,类型为字符串 假如 key
     * 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 当
     * key 不存在时，返回 nil(程序中返回为null) ，否则，返回 key 的值。 如果 key 不是字符串类型，那么返回一个错误。
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                String value = jedis.get(key);
                returnResource(jedis);
                return value;
            } else {
                returnResource(jedis);
                return null;
            }
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }

    }

    /*
     * 返回所有(一个或多个)给定 key 的值。 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil
     * 。因此，该命令永不失败。 可用版本：>= 1.0.0 时间复杂度:O(N) , N 为给定 key 的数量。 返回值：一个包含所有给定 key
     * 的值的列表。
     *
     * MGET redis mongodb mysql
     */

    public List<String> mget(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<String> values = jedis.mget(keys);
            returnResource(jedis);
            return values;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * SETNX key value
     *
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     *
     * 若给定的 key 已经存在，则 SETNX 不做任何动作。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 设置成功，返回 1 。 设置失败，返回 0 。
     */
    public void setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.setnx(key, value);

            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
     *
     * 即使只有一个给定 key 已存在， MSETNX 也会拒绝执行所有给定 key 的设置操作。
     *
     * MSETNX 是原子性的，因此它可以用作设置多个不同 key 表示不同字段(field)的唯一性逻辑对象(unique logic
     * object)，所有字段要么全被设置，要么全不被设置。
     *
     * 可用版本： >= 1.0.1 时间复杂度： O(N)， N 为要设置的 key 的数量。 返回值： 当所有 key 都成功设置，返回 1 。
     * 如果所有给定 key 都设置失败(至少有一个 key 已经存在)，那么返回 0 。
     */

    public Long msetnx(String... keysvalues) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.msetnx(keysvalues);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。 如果 key 不存在， APPEND
     * 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。 可用版本： >= 2.0.0 时间复杂度： 平摊O(1)
     * 返回值： 追加 value 之后， key 中字符串的长度。 APPEND
     * 可以为一系列定长(fixed-size)数据(sample)提供一种紧凑的表示方式，通常称之为时间序列。 可以考虑使用 UNIX
     * 时间戳作为时间序列的键名，这样一来，可以避免单个 key 因为保存过大的时间序列而占用大量内存，另一方面，也可以节省下大量命名空间。
     *
     * @param key
     * @param value
     */
    public Long append(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            Long len = jedis.append(key, value);
            returnResource(jedis);
            return len;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回 key 所储存的字符串值的长度。 当 key 储存的不是字符串值时，返回一个错误。 可用版本： >= 2.2.0 复杂度： O(1)
     * 返回值： 字符串值的长度。当 key 不存在时，返回 0 。
     *
     * @param key
     */
    public Long strlen(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long len = jedis.strlen(key);

            returnResource(jedis);
            return len;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回 key 中字符串值的子字符串: 字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * 负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。 GETRANGE
     * 通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求。 在 <= 2.0 的版本里，GETRANGE 被叫作
     * SUBSTR。 可用版本：>= 2.4.0 时间复杂度：O(N)， N 为要返回的字符串的长度。
     * 复杂度最终由字符串的返回值长度决定，但因为从已有字符串中取出子字符串的操作非常廉价
     * (cheap)，所以对于长度不大的字符串，该操作的复杂度也可看作O(1)。 返回值：截取得出的子字符串。 GETRANGE greeting 0
     * -1 # 从第一个到最后一个 GETRANGE greeting 0 1008611 # 值域范围不超过实际字符串，超过部分自动被符略
     */
    public String getRange(String key, long startOffset, long endOffset) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.getrange(key, startOffset, endOffset);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。 不存在的 key
     * 当作空白字符串处理。 SETRANGE 命令会确保字符串足够长以便将 value 设置在指定的偏移量上，如果给定 key
     * 原来储存的字符串长度比偏移量小(比如字符串只有 5 个字符长，但你设置的 offset 是 10 )，
     * 那么原字符和偏移量之间的空白将用零字节(zerobytes, "\x00" )来填充。 注意你能使用的最大偏移量是
     * 2^29-1(536870911) ，因为 Redis 字符串的大小被限制在 512 兆(megabytes)以内。
     * 如果你需要使用比这更大的空间，你可以使用多个 key 。 注意若首次内存分配成功之后，再对同一个 key 调用 SETRANGE
     * 操作，无须再重新内存。 可用版本：>= 2.2.0 时间复杂度：对小(small)的字符串，平摊复杂度O(1)。(关于什么字符串是”小”的，请参考
     * APPEND 命令) 否则为O(M)， M 为 value 参数的长度。 返回值：被 SETRANGE 修改之后，字符串的长度。
     *
     * SETRANGE empty_string 5 "Redis!" # 对不存在的 key 使用 SETRANGE (integer) 11 GET
     * empty_string # 空白处被"\x00"填充 "\x00\x00\x00\x00\x00Redis!" 因为有了 SETRANGE 和
     * GETRANGE 命令，你可以将 Redis 字符串用作具有O(1)随机访问时间的线性数组，这在很多真实用例中都是非常快速且高效的储存方式，
     * 具体请参考 APPEND 命令的『模式：时间序列』部分。
     */
    public Long setRange(String key, long offset, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long len = jedis.setrange(key, offset, value);
            returnResource(jedis);
            return len;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。 当 key 存在但不是字符串类型时，返回一个错误。
     * 可用版本：>= 1.0.0 时间复杂度：O(1) 返回值：返回给定 key 的旧值。 当 key 没有旧值时，也即是， key 不存在时，返回
     * nil
     */

    public String getset(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String oldvalue = jedis.getSet(key, value);

            returnResource(jedis);
            return oldvalue;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将 key 中储存的数字值增一。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * 这是一个针对字符串的操作，因为 Redis 没有专用的整数类型，所以 key 内储存的字符串被解释为十进制 64 位有符号整数来执行 INCR
     * 操作。 可用版本：>= 1.0.0 时间复杂度：O(1) 返回值：执行 INCR 命令之后 key 的值。
     */

    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.incr(key);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将 key 所储存的值加上增量 increment 。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY
     * 命令。 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * 可用版本：>= 1.0.0 时间复杂度：O(1)返回值：加上 increment 之后， key 的值。
     */
    public Long incrBy(String key, long integer) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.incrBy(key, integer);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将 key 中储存的数字值减一。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作(会变为负数)。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。 本操作的值限制在 64 位(bit)有符号数字表示之内。 可用版本：>=
     * 1.0.0 时间复杂度：O(1) 返回值：执行 DECR 命令之后 key 的值。
     */
    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.decr(key);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将 key 所储存的值减去减量 decrement 。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY
     * 操作。 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * 可用版本：>= 1.0.0 时间复杂度：O(1)返回值：减去 decrement 之后， key 的值。
     */
    public Long decrBy(String key, long integer) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.decrBy(key, integer);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /* ======================对List的操作命令======================================= */
    /*
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH
     * mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和
     * LPUSH mylist c 三个命令。
     *
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     *
     * 当 key 存在但不是列表类型时，返回一个错误。 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 执行 LPUSH
     * 命令后，列表的长度
     */

    public Long lpush(String key, String... strings) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.lpush(key, strings);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * <p>
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist
     * a b c ，得出的结果列表为 a b c ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH
     * mylist c 。
     * <p>
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。
     * <p>
     * 当 key 存在但不是列表类型时，返回一个错误。 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 执行 RPUSH
     * 操作后，表的长度。
     */
    public Long rpush(String key, String string) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long ret = jedis.rpush(key, string);
            returnResource(jedis);
            return ret;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
     *
     * 和 LPUSH 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
     *
     * 可用版本： >= 2.2.0 时间复杂度： O(1) 返回值： LPUSHX 命令执行之后，表的长度。
     */

    public Long lpushx(String key, String string) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.lpushx(key, string);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。
     *
     * 和 RPUSH 命令相反，当 key 不存在时， RPUSHX 命令什么也不做。
     *
     * 可用版本： >= 2.2.0 时间复杂度： O(1) 返回值： RPUSHX 命令执行之后，表的长度。
     */
    public Long rpushx(String key, String string) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.rpushx(key, string);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 移除并返回列表 key 的头元素。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 列表的头元素。 当 key 不存在时，返回 nil 。
     */
    public String lpop(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.lpop(key);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 移除并返回列表 key 的尾元素。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 列表的尾元素。 当 key 不存在时，返回 nil 。
     */
    public String rpop(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.rpop(key);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 命令 RPOPLPUSH 在一个原子时间内，执行以下两个动作：
     *
     * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端。 将 source 弹出的元素插入到列表 destination ，作为
     * destination 列表的的头元素。 举个例子，你有两个列表 source 和 destination ， source 列表有元素 a,
     * b, c ， destination 列表有元素 x, y, z ，执行 RPOPLPUSH source destination 之后，
     * source 列表包含元素 a, b ， destination 列表包含元素 c, x, y, z ，并且元素 c 会被返回给客户端。
     *
     * 如果 source 不存在，值 nil 被返回，并且不执行其他动作。
     *
     * 如果 source 和 destination
     * 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作。
     *
     * 可用版本： >= 1.2.0 时间复杂度： O(1) 返回值： 被弹出的元素。
     */
    public String rpoplpush(String srckey, String dstkey) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.rpoplpush(srckey, dstkey);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 获取key这个List，从第几个元素到第几个元素 LRANGE key start
     * stop返回列表key中指定区间内的元素，区间以偏移量start和stop指定。
     * 下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。
     * 也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。
     *
     * @param key   List别名
     * @param start 开始下标
     * @param end   结束下标
     * @return
     */
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<String> ret = jedis.lrange(key, start, end);
            returnResource(jedis);
            return ret;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 获取key这个List，从第几个元素到第几个元素 LRANGE key start
     * stop返回列表key中指定区间内的元素，区间以偏移量start和stop指定。
     * 下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。
     * 也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。
     *
     * @param key   List别名
     * @param start 开始下标
     * @param end   结束下标
     * @return
     */
    public List<String> lrange(int idx, String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getResource(idx);
            List<String> ret = jedis.lrange(key, start, end);
            returnResource(jedis);
            return ret;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回列表 key 的长度。
     *
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     *
     * 如果 key 不是列表类型，返回一个错误。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 列表 key 的长度。
     */
    public Long llen(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.llen(key);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将列表 key 下标为 index 的元素的值设置为 value 。
     *
     * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
     *
     * 关于列表下标的更多信息，请参考 LINDEX 命令。
     *
     * 可用版本： >= 1.0.0 时间复杂度： 对头元素或尾元素进行 LSET 操作，复杂度为 O(1)。 其他情况下，为 O(N)， N
     * 为列表的长度。 返回值： 操作成功返回 ok ，否则返回错误信息。
     */
    public void lset(String key, long index, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.lset(key, index, value);

            returnResource(jedis);

        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     *
     * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。
     *
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     *
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * 当 key 不是列表类型时，返回一个错误。
     *
     * LTRIM 命令通常和 LPUSH 命令或 RPUSH 命令配合使用，举个例子：
     *
     * LPUSH log newest_log LTRIM log 0 99 这个例子模拟了一个日志程序，每次将最新日志 newest_log 放到
     * log 列表中，并且只保留最新的 100 项。注意当这样使用 LTRIM 命令时，时间复杂度是O(1)，因为平均情况下，每次只有一个元素被移除。
     *
     * 注意LTRIM命令和编程语言区间函数的区别
     *
     * 假如你有一个包含一百个元素的列表 list ，对该列表执行 LTRIM list 0 10 ，结果是一个包含11个元素的列表，这表明 stop
     * 下标也在 LTRIM 命令的取值范围之内(闭区间)，这和某些语言的区间函数可能不一致，比如Ruby的 Range.new 、
     * Array#slice 和Python的 range() 函数。
     *
     * 超出范围的下标
     *
     * 超出范围的下标值不会引起错误。
     *
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start > stop ， LTRIM
     * 返回一个空列表(因为 LTRIM 已经将整个列表清空)。
     *
     * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(N)， N 为被移除的元素的数量。 返回值: 命令执行成功时，返回 ok 。
     */
    public String ltrim(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String type = jedis.ltrim(key, start, end);

            returnResource(jedis);
            return type;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回列表 key 中，下标为 index 的元素。
     *
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     *
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * 如果 key 不是列表类型，返回一个错误。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(N)， N 为到达下标 index 过程中经过的元素数量。 因此，对列表的头元素和尾元素执行
     * LINDEX 命令，复杂度为O(1)。 返回值: 列表中下标为 index 的元素。 如果 index 参数的值不在列表的区间范围内(out of
     * range)，返回 nil 。
     */

    public String lindex(String key, long index) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.lindex(key, index);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。
     *
     * 当 pivot 不存在于列表 key 时，不执行任何操作。
     *
     * 当 key 不存在时， key 被视为空列表，不执行任何操作。
     *
     * 如果 key 不是列表类型，返回一个错误。
     *
     * 可用版本： >= 2.2.0 时间复杂度: O(N)， N 为寻找 pivot 过程中经过的元素数量。 返回值:
     * 如果命令执行成功，返回插入操作完成之后，列表的长度。 如果没有找到 pivot ，返回 -1 。 如果 key 不存在或为空列表，返回 0 。
     * 其中 ： LIST_POSITION表示位置方位 LIST_POSITION.AFTER; LIST_POSITION.BEFORE
     */

    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value1 = jedis.linsert(key, where, pivot, value);

            returnResource(jedis);
            return value1;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     *
     * count 的值可以是以下几种：
     *
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。 count < 0 :
     * 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。 count = 0 : 移除表中所有与 value
     * 相等的值。 可用版本： >= 1.0.0 时间复杂度： O(N)， N 为列表的长度。 返回值： 被移除元素的数量。 因为不存在的 key
     * 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
     */
    public Long lrem(String key, long count, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value1 = jedis.lrem(key, count, value);

            returnResource(jedis);
            return value1;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     *
     * count 的值可以是以下几种：
     *
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。 count < 0 :
     * 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。 count = 0 : 移除表中所有与 value
     * 相等的值。 可用版本： >= 1.0.0 时间复杂度： O(N)， N 为列表的长度。 返回值： 被移除元素的数量。 因为不存在的 key
     * 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
     */
    public Long lrem(int idx, String key, long count, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource(idx);
            Long value1 = jedis.lrem(key, count, value);

            returnResource(jedis);
            return value1;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /* ==========================对set的操作命令==================================== */
    /*
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     *
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
     *
     * 当 key 不是集合类型时，返回一个错误。
     *
     * 在Redis2.4版本以前， SADD 只接受单个 member 值。 可用版本： >= 1.0.0 时间复杂度: O(N)， N
     * 是被添加的元素的数量。 返回值: 被添加到集合中的新元素的数量，不包括被忽略的元素。
     */

    public Long sadd(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.sadd(key, members);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回集合 key 的基数(集合中元素的数量)。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(1) 返回值： 集合的基数。 当 key 不存在时，返回 0 。
     */
    public Long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.scard(key);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * SDIFF key [key ...]
     *
     * 返回一个集合的全部成员，该集合是所有给定集合之间的差集（即：非公共成员）。
     *
     * 不存在的 key 被视为空集。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(N)， N 是所有给定集合的成员数量之和。 返回值: 差集成员的列表。
     */
    public Set<String> sdiff(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> strings = jedis.sdiff(keys);

            returnResource(jedis);
            return strings;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地返回结果集。
     *
     * 如果 destination 集合已经存在，则将其覆盖。
     *
     * destination 可以是 key 本身。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(N)， N 是所有给定集合的成员数量之和。 返回值: 结果集中的元素数量。
     */
    public Long sdiffstore(String dstkey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.sdiffstore(dstkey, keys);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回一个集合的全部成员，该集合是所有给定集合的交集(即：公共成员)。
     *
     * 不存在的 key 被视为空集。
     *
     * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(N * M)， N 为给定集合当中基数最小的集合， M 为给定集合的个数。 返回值:
     * 交集成员的列表。
     */
    public Set<String> sinter(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> strings = jedis.sinter(keys);

            returnResource(jedis);
            return strings;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 这个命令类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
     *
     * 如果 destination 集合已经存在，则将其覆盖。
     *
     * destination 可以是 key 本身。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(N * M)， N 为给定集合当中基数最小的集合， M 为给定集合的个数。 返回值:
     * 结果集中的成员数量。
     */
    public Long sinterstore(String dstkey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.sinterstore(dstkey, keys);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回集合 key 中的所有成员。
     *
     * 不存在的 key 被视为空集合。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(N)， N 为集合的基数。 返回值: 集合中的所有成员。
     */
    public Set<?> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<?> set = jedis.smembers(key);

            returnResource(jedis);
            return set;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 判断 member 元素是否集合 key 的成员。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(1) 返回值: 如果 member 元素是集合的成员，返回 1 。 如果 member
     * 元素不是集合的成员，或 key 不存在，返回 0
     */
    public Boolean sismember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Boolean bool = jedis.sismember(key, member);
            returnResource(jedis);
            return bool;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将 member 元素从 source 集合移动到 destination 集合。
     *
     * SMOVE 是原子性操作。
     *
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从
     * source 集合中被移除，并添加到 destination 集合中去。
     *
     * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
     *
     * 当 source 或 destination 不是集合类型时，返回一个错误。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(1) 返回值: 如果 member 元素被成功移除，返回 1 。 如果 member 元素不是
     * source 集合的成员，并且没有任何操作对 destination 集合执行，那么返回 0 。
     */
    public Long smove(String srckey, String dstkey, String member) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.smove(srckey, dstkey, member);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 移除并返回集合中的一个随机元素。
     *
     * 如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用 SRANDMEMBER 命令。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(1) 返回值: 被移除的随机元素。 当 key 不存在或 key 是空集时，返回 nil 。
     */
    public String spop(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.spop(key);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。
     *
     * 从 Redis 2.6 版本开始， SRANDMEMBER 命令接受可选的 count 参数：
     *
     * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count
     * 大于等于集合基数，那么返回整个集合。 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count
     * 的绝对值。 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 SRANDMEMBER
     * 则仅仅返回随机元素，而不对集合进行任何改动。
     *
     * 可用版本： >= 1.0.0 时间复杂度: 只提供 key 参数时为 O(1) 。 如果提供了 count 参数，那么为 O(N) ，N
     * 为返回数组的元素个数。 返回值: 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。 如果提供了 count
     * 参数，那么返回一个数组；如果集合为空，返回空数组。
     */
    public String srandmember(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.srandmember(key);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回一个集合的全部成员，该集合是所有给定集合的并集(即：全部包含)。
     *
     * 不存在的 key 被视为空集。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(N)， N 是所有给定集合的成员数量之和。 返回值: 并集成员的列表。
     */
    public Set<String> sunion(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> strings = jedis.sunion(keys);

            returnResource(jedis);
            return strings;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 这个命令类似于 SUNION 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
     *
     * 如果 destination 已经存在，则将其覆盖。
     *
     * destination 可以是 key 本身。
     *
     * 可用版本： >= 1.0.0 时间复杂度: O(N)， N 是所有给定集合的成员数量之和。 返回值: 结果集中的元素数量。
     */
    public Long sunionstore(String dstkey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.sunionstore(dstkey, keys);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     * <p>
     * 当 key 不是集合类型，返回一个错误。
     * <p>
     * 在 Redis 2.4 版本以前， SREM 只接受单个 member 值。 可用版本： >= 1.0.0 时间复杂度: O(N)， N 为给定
     * member 元素的数量。 返回值: 被成功移除的元素的数量，不包括被忽略的元素。
     */
    public Long srem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long len = jedis.srem(key, members);
            returnResource(jedis);
            return len;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * =================================对SortedSet的操作命令==========================
     */
    /*
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     *
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该
     * member 在正确的位置上。
     *
     * score 值可以是整数值或双精度浮点数。
     *
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     *
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * 在 Redis 2.4 版本以前， ZADD 每次只能添加一个元素。 可用版本： >= 1.2.0 时间复杂度: O(M*log(N))， N
     * 是有序集的基数， M 为成功添加的新成员的数量。 返回值: 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public Long zadd(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.zadd(key, score, member);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回有序集 key 的基数。
     *
     * 可用版本： >= 1.2.0 时间复杂度: O(1) 返回值: 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回
     * 0 。
     */
    public Long zcard(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.zcard(key);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
     *
     * 关于参数 min 和 max 的详细使用方法，请参考 ZRANGEBYSCORE 命令。 可用版本： >= 2.0.0 时间复杂度:
     * O(log(N)+M)， N 为有序集的基数， M 为值在 min 和 max 之间的元素的数量。 返回值: score 值在 min 和 max
     * 之间的成员的数量。
     */
    public Long zcount(String key, String min, String max) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.zcount(key, min, max);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 。
     *
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让
     * member 的 score 值减去 5 。
     *
     * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key
     * increment member 。
     *
     * 当 key 不是有序集类型时，返回一个错误。
     *
     * score 值可以是整数值或双精度浮点数。 可用版本： >= 1.2.0 时间复杂度: O(log(N)) 返回值: member 成员的新
     * score 值，以字符串形式表示。
     */

    public Double zincrby(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Double value = jedis.zincrby(key, score, member);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回有序集 key 中，指定区间内的成员。
     *
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     *
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
     *
     * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
     *
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。 超出范围的下标并不会引起错误。 比如说，当 start
     * 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。 另一方面，假如 stop
     * 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。 可以通过使用 WITHSCORES 选项，来让成员和它的
     * score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
     * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。 可用版本： >= 1.2.0 时间复杂度: O(log(N)+M)， N
     * 为有序集的基数，而 M 为结果集的基数。 返回值: 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */

    public Set<String> zrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> ret = jedis.zrange(key, start, end);
            returnResource(jedis);
            return ret;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回有序集 key 中，指定区间内的成员。
     *
     *
     * 其中成员的位置按 score 值递减(从大到小)来排列。
     *
     * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order)排列。
     *
     * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。 可用版本： >= 1.2.0
     * 时间复杂度: O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。 返回值: 指定区间内，带有 score
     * 值(可选)的有序集成员的列表。
     */
    public Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> ret = jedis.zrevrange(key, start, end);
            returnResource(jedis);
            return ret;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
     *
     * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。
     *
     * 使用 ZREVRANK 命令可以获得成员按 score 值递减(从大到小)排列的排名。
     *
     * 可用版本： >= 2.0.0 时间复杂度: O(log(N)) 返回值: 如果 member 是有序集 key 的成员，返回 member
     * 的排名。 如果 member 不是有序集 key 的成员，返回 nil 。
     */
    public Long zrank(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.zrank(key, member);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
     *
     * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。
     *
     * 使用 ZRANK 命令可以获得成员按 score 值递增(从小到大)排列的排名。 可用版本： >= 2.0.0 时间复杂度: O(log(N))
     * 返回值:
     *
     * 如果 member 是有序集 key 的成员，返回 member 的排名。
     *
     * 如果 member 不是有序集 key 的成员，返回 nil 。
     */
    public Long zrevrank(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.zrevrank(key, member);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回有序集 key 中，成员 member 的 score 值。
     *
     * 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
     *
     * 可用版本： >= 1.2.0 时间复杂度: O(1) 返回值: member 成员的 score 值，以字符串形式表示。
     */
    public Double zscore(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Double value = jedis.zscore(key, member);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     *
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * 在 Redis 2.4 版本以前， ZREM 每次只能删除一个元素。 可用版本： >= 1.2.0 时间复杂度: O(M*log(N))， N
     * 为有序集的基数， M 为被成功移除的成员的数量。 返回值: 被成功移除的成员的数量，不包括被忽略的成员。
     */

    public Long zrem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long len = jedis.zrem(key, members);

            returnResource(jedis);
            return len;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 移除有序集 key 中，指定排名(rank)区间内的所有成员。
     *
     * 区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。
     *
     *
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     *
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。 可用版本： >= 2.0.0 时间复杂度:
     * O(log(N)+M)， N 为有序集的基数，而 M 为被移除成员的数量。 返回值: 被移除成员的数量。
     */
    public Long zremrangeByRank(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long len = jedis.zremrangeByRank(key, start, end);

            returnResource(jedis);
            return len;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
     *
     * 自版本2.1.6开始， score 值等于 min 或 max 的成员也可以不包括在内，详情请参见 ZRANGEBYSCORE 命令。 可用版本：
     * >= 1.2.0 时间复杂度: O(log(N)+M)， N 为有序集的基数，而 M 为被移除成员的数量。 返回值: 被移除成员的数量。
     */
    public Long zremrangeByScore(String key, String start, String end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long len = jedis.zremrangeByScore(key, start, end);

            returnResource(jedis);
            return len;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * ==============================对Hash的操作命令==================================
     */

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。 如果域
     * field 已经存在于哈希表中，旧值将被覆盖。 可用版本： >= 2.0.0 时间复杂度： O(1) 返回值： 如果 field
     * 是哈希表中的一个新建域，并且值设置成功，返回 1 。 如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
     */
    public void hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.hset(key, field, value);
            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。 如果域
     * field 已经存在于哈希表中，旧值将被覆盖。 可用版本： >= 2.0.0 时间复杂度： O(1) 返回值： 如果 field
     * 是哈希表中的一个新建域，并且值设置成功，返回 1 。 如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
     */
    public void hset(Integer dbIdx, String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource(dbIdx);
            jedis.hset(key, field, value);
            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     *
     * 若域 field 已经存在，该操作无效。
     *
     * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(1) 返回值： 设置成功，返回 1 。 如果给定域已经存在且没有操作被执行，返回 0 。
     */

    public void hsetnx(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.hsetnx(key, field, value);
            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * <p>
     * 此命令会覆盖哈希表中已存在的域。
     * <p>
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     * <p>
     * 可用版本： >= 2.0.0 时间复杂度： O(N)， N 为 field-value 对的数量。 返回值： 如果命令执行成功，返回 OK 。 当
     * key 不是哈希表(hash)类型时，返回一个错误。 redis> HMSET website google www.google.com
     * yahoo www.yahoo.com
     * <p>
     * redis> HGET website google "www.google.com"
     */
    public void hmset(String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.hmset(key, map);
            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回哈希表 key 中，一个或多个给定域的值。
     *
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     *
     * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil值的表。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(N)， N 为给定域的数量。 返回值：
     * 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
     */
    public List<String> hmget2List(String key, String... fields) {// 实际上为hmget
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<String> value = jedis.hmget(key, fields);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。 如果 key 已经存在， SETEX
     * 命令将覆写旧值。 SETEX 是一个原子性(atomic)操作，关联值和设置生存时间两个动作会在同一时间内完成，该命令在 Redis
     * 用作缓存时，非常实用。 可用版本：>= 2.0.0 时间复杂度：O(1) 返回值：设置成功时返回 OK 。 当 seconds
     * 参数不合法时，返回一个错误。
     */
    public void setex(String key, int seconds, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.setex(key, seconds, value);
            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。 可用版本： >= 2.0.0 时间复杂度： O(1) 返回值： 给定域的值。
     * 当给定域不存在或是给定 key 不存在时，返回 nil 。
     */
    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.hget(key, field);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。 可用版本： >= 2.0.0 时间复杂度： O(1) 返回值： 给定域的值。
     * 当给定域不存在或是给定 key 不存在时，返回 nil 。
     * 指定 db 索引
     */
    public String hget(Integer dbIdx, String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getResource(dbIdx);
            String value = jedis.hget(key, field);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 返回哈希表 key 中，所有的域和值。
     * <p>
     * 在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
     * <p>
     * 可用版本： >= 2.0.0 时间复杂度： O(N)， N 为哈希表的大小。 返回值： 以列表形式返回哈希表的域和域的值。 若 key
     * 不存在，返回空列表。
     */
    public Map<String, String> hmget(String key) {// 实际上为hgetAll
        Jedis jedis = null;
        try {
            jedis = getResource();
            Map<String, String> map = jedis.hgetAll(key);

            returnResource(jedis);
            return map;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }

    }

    /**
     * 返回哈希表 key 中，所有的域和值。
     * <p>
     * 在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
     * <p>
     * 可用版本： >= 2.0.0 时间复杂度： O(N)， N 为哈希表的大小。 返回值： 以列表形式返回哈希表的域和域的值。 若 key
     * 不存在，返回空列表。
     */
    public Map<String, String> hmget(Integer dbIdx, String key) {// 实际上为hgetAll
        Jedis jedis = null;
        try {
            jedis = getResource(dbIdx);
            Map<String, String> map = jedis.hgetAll(key);

            returnResource(jedis);
            return map;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }

    }


    /*
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     *
     * 增量也可以为负数，相当于对给定域进行减法操作。
     *
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     *
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     *
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     *
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(1) 返回值： 执行 HINCRBY 命令之后，哈希表 key 中域 field 的值。
     */
    public Long hincrBy(String key, String field, long value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value1 = jedis.hincrBy(key, field, value);
            returnResource(jedis);
            return value1;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 返回哈希表 key 中所有域的值。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(N)， N 为哈希表的大小。 返回值： 一个包含哈希表中所有值的表。 当 key
     * 不存在时，返回一个空表。
     */
    public List<String> hvals(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<String> hvalues = jedis.hvals(key);
            returnResource(jedis);
            return hvalues;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }

    }

    /*
     * 返回哈希表 key 中的所有域。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(N)， N 为哈希表的大小。 返回值： 一个包含哈希表中所有域的表。 当 key
     * 不存在时，返回一个空表。
     */
    public Set<String> hkeys(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> hkeys = jedis.hkeys(key);
            returnResource(jedis);
            return hkeys;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }

    }

    /*
     * 返回哈希表 key 中域的数量。
     *
     * 时间复杂度： O(1) 返回值： 哈希表中域的数量。 当 key 不存在时，返回 0 。
     */
    public Long hlen(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.hlen(key);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 查看哈希表 key 中，给定域 field 是否存在。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(1) 返回值： 如果哈希表含有给定域，返回 1 。 如果哈希表不含有给定域，或 key
     * 不存在，返回 0 。
     */
    public Boolean hexists(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Boolean bool = jedis.hexists(key, field);
            returnResource(jedis);
            return bool;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * 在Redis2.4以下的版本里， HDEL 每次只能删除单个域，如果你需要在一个原子时间内删除多个域，请将命令包含在 MULTI / EXEC
     * 块内。 可用版本： >= 2.0.0 时间复杂度: O(N)， N 为要删除的域的数量。 返回值: 被成功移除的域的数量，不包括被忽略的域。
     */
    public Long hdel(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.hdel(key, fields);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * 在Redis2.4以下的版本里， HDEL 每次只能删除单个域，如果你需要在一个原子时间内删除多个域，请将命令包含在 MULTI / EXEC
     * 块内。 可用版本： >= 2.0.0 时间复杂度: O(N)， N 为要删除的域的数量。 返回值: 被成功移除的域的数量，不包括被忽略的域。
     */
    public Long hdel(Integer dbIdx, String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = getResource(dbIdx);
            Long value = jedis.hdel(key, fields);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }


    /* =============================对key的操作命令=============================== */

    /**
     * 删除给定的一个或多个 key 。
     * <p>
     * 不存在的 key 会被忽略。
     * <p>
     * 可用版本： >= 1.0.0 时间复杂度： O(N)， N 为被删除的 key 的数量。 删除单个字符串类型的 key ，时间复杂度为O(1)。
     * 删除单个列表、集合、有序集合或哈希表类型的 key ，时间复杂度为O(M)， M 为以上数据结构内的元素数量。 返回值： 被删除 key 的数量。
     */
    public long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            long value = jedis.del(keys);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 删除给定的一个或多个 key 。
     * <p>
     * 不存在的 key 会被忽略。
     * <p>
     * 可用版本： >= 1.0.0 时间复杂度： O(N)， N 为被删除的 key 的数量。 删除单个字符串类型的 key ，时间复杂度为O(1)。
     * 删除单个列表、集合、有序集合或哈希表类型的 key ，时间复杂度为O(M)， M 为以上数据结构内的元素数量。 返回值： 被删除 key 的数量。
     */
    public long del(Integer dbIdx, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getResource(dbIdx);
            long value = jedis.del(keys);

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 检查给定 key 是否存在。 可用版本：>= 1.0.0 时间复杂度：O(1) 返回值：若 key 存在，返回 1 ，否则返回 0 。
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Boolean bool = jedis.exists(key);
            returnResource(jedis);
            return bool;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 为给定key设置生命周期
     *
     * @param key
     * @param seconds 生命周期 秒为单位
     */
    public void expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.expire(key, seconds);

            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
     *
     * 可用版本： >= 1.2.0 时间复杂度： O(1) 返回值： 如果生存时间设置成功，返回 1 。 当 key 不存在或没办法设置生存时间，返回
     * 0 。
     */
    public void expireAt(String key, long unixTime) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.expireAt(key, unixTime);
            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 查找所有符合给定模式 pattern 的 key
     *
     * KEYS * 匹配数据库中所有 key 。 KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。 KEYS h*llo
     * 匹配 hllo 和 heeeeello 等。 KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。 特殊符号用
     * \ 隔开
     *
     * KEYS 的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，如果你需要从一个数据集中查找特定的 key ，你最好还是用 Redis
     * 的集合结构(set)来代替。 可用版本： >= 1.0.0 时间复杂度： O(N)， N 为数据库中 key 的数量。 返回值： 符合给定模式的
     * key 列表。
     */
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> keys = jedis.keys(pattern);
            returnResource(jedis);
            return keys;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中。
     * <p>
     * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，或者 key 不存在于当前数据库，那么 MOVE 没有任何效果。
     * <p>
     * 因此，也可以利用这一特性，将 MOVE 当作锁(locking)原语(primitive)。
     * <p>
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 移动成功返回 1 ，失败则返回 0 。
     */
    public void move(String key, int dbIndex) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.move(key, dbIndex);

            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }

    }

    /*
     * 将 key 改名为 newkey 。
     *
     * 当 key 和 newkey 相同，或者 key 不存在时，返回一个错误。
     *
     * 当 newkey 已经存在时， RENAME 命令将覆盖旧值。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 改名成功时提示 OK ，失败时候返回一个错误。
     */
    public void rename(String oldkey, String newkey) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.rename(oldkey, newkey);

            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }

    }

    /*
     * 当且仅当 newkey 不存在时，将 key 改名为 newkey 。
     *
     * 当 key 不存在时，返回一个错误。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 修改成功时，返回 1 。 如果 newkey 已经存在，返回 0
     */
    public void renamenx(String oldkey, String newkey) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.renamenx(oldkey, newkey);

            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }

    }

    /*
     * 从当前数据库中随机返回(不删除)一个 key 。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 当数据库不为空时，返回一个 key 。 当数据库为空时，返回 nil 。
     */
    public String randomKey() {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.randomKey();
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     *
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 当 key 不存在时，返回 -2 。 当 key
     * 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。 在 Redis 2.8 以前，当 key
     * 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1 。
     */

    public long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            long value = jedis.ttl(key);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /**
     * 返回 key 所储存的值的类型。
     * <p>
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： none (key不存在) string (字符串) list (列表) set
     * (集合) zset (有序集) hash (哈希表)
     */
    public String type(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String type = jedis.type(key);
            returnResource(jedis);
            return type;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 方法很多，需要参照官方 API
     */
    public List<String> sort(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<String> value = jedis.sort(key);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    public List<String> sort(String key, SortingParams sortingParameters) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<String> value = jedis.sort(key, sortingParameters);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * ===================================Server ==========================
     */
    /*
     * 返回当前数据库的 key 的数量。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 当前数据库的 key 的数量。
     */
    public Long dbSize() {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long value = jedis.dbSize();

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * CONFIG SET 命令可以动态地调整 Redis 服务器的配置(configuration)而无须重启。
     *
     * 你可以使用它修改配置参数，或者改变 Redis 的持久化(Persistence)方式。
     *
     * CONFIG SET 可以修改的配置参数可以使用命令 CONFIG GET * 来列出，所有被 CONFIG SET 修改的配置参数都会立即生效。
     *
     * 关于 CONFIG SET 命令的更多消息，请参见命令 CONFIG GET 的说明。
     *
     * 关于如何使用 CONFIG SET 命令修改 Redis 持久化方式，请参见 Redis Persistence 。
     *
     * 可用版本： >= 2.0.0 时间复杂度： 不明确 返回值： 当设置成功时返回 OK ，否则返回一个错误。
     */
    public List<String> configGet(String pattern) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<String> value = jedis.configGet(pattern);
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * CONFIG SET parameter value
     *
     * CONFIG SET 命令可以动态地调整 Redis 服务器的配置(configuration)而无须重启。
     *
     * 你可以使用它修改配置参数，或者改变 Redis 的持久化(Persistence)方式。
     *
     * CONFIG SET 可以修改的配置参数可以使用命令 CONFIG GET * 来列出，所有被 CONFIG SET 修改的配置参数都会立即生效。
     *
     * 关于 CONFIG SET 命令的更多消息，请参见命令 CONFIG GET 的说明。
     *
     * 关于如何使用 CONFIG SET 命令修改 Redis 持久化方式，请参见 Redis Persistence 。
     *
     * 可用版本： >= 2.0.0 时间复杂度： 不明确 返回值： 当设置成功时返回 OK ，否则返回一个错误。
     */
    public String configSet(String parameter, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value1 = jedis.configSet(parameter, value);

            returnResource(jedis);
            return value1;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 清空整个 Redis 服务器的数据(删除所有数据库的所有 key )。
     *
     * 此命令从不失败。
     *
     * 可用版本： >= 1.0.0 时间复杂度： 尚未明确 返回值： 总是返回 OK 。
     */
    public void flushAll() {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.flushAll();

            returnResource(jedis);

        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 清空当前数据库中的所有 key。
     *
     * 此命令从不失败。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 总是返回 OK 。
     */
    public void flushDB() {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.flushDB();

            returnResource(jedis);

        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     *
     * 以一种易于解释（parse）且易于阅读的格式，返回关于 Redis 服务器的各种信息和统计数值。
     *
     * 通过给定可选的参数 section ，可以让命令只返回某一部分的信息：
     */
    public String info() {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.info();
            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * MONITOR
     *
     * 实时打印出 Redis 服务器接收到的命令，调试用。
     *
     * 可用版本： >= 1.0.0 时间复杂度： 不明确 返回值： 总是返回 OK 。
     */
    public void monitor(JedisMonitor jedisMonitor) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.monitor(jedisMonitor);
            returnResource(jedis);

        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /* ===================Connection====================== */
    /*
     * 使用客户端向 Redis 服务器发送一个 PING ，如果服务器运作正常的话，会返回一个 PONG 。
     *
     * 通常用于测试与服务器的连接是否仍然生效，或者用于测量延迟值。
     *
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 如果连接正常就返回一个 PONG ，否则返回一个连接错误。
     */

    public String ping() {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String value = jedis.ping();

            returnResource(jedis);
            return value;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * ===================================Pub/Sub（发布/订阅）==========================
     */

    /*
     * 将信息 message 发送到指定的频道 channel 。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(N+M)，其中 N 是频道 channel 的订阅者数量，而 M
     * 则是使用模式订阅(subscribed patterns)的客户端的数量。 返回值： 接收到信息 message 的订阅者数量。
     */
    public Long publish(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long len = jedis.publish(channel, message);

            returnResource(jedis);
            return len;
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 订阅给定的一个或多个频道的信息。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(N)，其中 N 是订阅的频道的数量。 返回值： 接收到的信息(请参见下面的代码说明)。
     */
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.subscribe(jedisPubSub, channels);

            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

    /*
     * 订阅一个或多个符合给定模式的频道。
     *
     * 每个模式以 * 作为匹配符，比如 it* 匹配所有以 it 开头的频道( it.news 、 it.blog 、 it.tweets 等等)，
     * news.* 匹配所有以 news. 开头的频道( news.it 、 news.global.today 等等)，诸如此类。
     *
     * 可用版本： >= 2.0.0 时间复杂度： O(N)， N 是订阅的模式的数量
     */
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.psubscribe(jedisPubSub, patterns);

            returnResource(jedis);
        } catch (Exception e) {

            if (jedis != null) {
                returnBrokenResource(jedis);
            }
            throw new JedisException(e);
        }
    }

}

