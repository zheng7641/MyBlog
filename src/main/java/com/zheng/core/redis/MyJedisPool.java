package com.zheng.core.redis;

import redis.clients.jedis.*;

import java.util.ResourceBundle;

/**
 * @author zhengct on 2018/4/17
 */
public class MyJedisPool {
    // jedis池
    private static JedisPool pool;
//    // shardedJedis池
//    private static ShardedJedisPool shardPool;
    //dbidx
    private static Integer dbIdx=0;
    //学生信息更新时间
    private static Long userTimeOut;
    //学校，学院，班级超时更新时间
    private static Long sccTimeOut;
    //学生，教师所在教学班更新时间
    private static Long logicClassTimeOut;

    // 静态代码初始化池配置
    static {
        // 加载redis配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null) {
            throw new IllegalArgumentException("[redis.properties] is not found!");
        }
        String strTimeout = bundle.getString("jedis.pool.connectionTimeout");

        // 创建jedis池配置实例
        final JedisPoolConfig config = new JedisPoolConfig();
        // 设置池配置项值
        config.setMaxTotal(Integer.valueOf(bundle.getString("jedis.pool.maxActive")));
        config.setMaxIdle(Integer.valueOf(bundle.getString("jedis.pool.maxIdle")));
        config.setMinIdle(Integer.valueOf(bundle.getString("jedis.pool.minIdle")));
        config.setMaxWaitMillis(Long.valueOf(bundle.getString("jedis.pool.maxWait")));
        config.setTestOnBorrow(Boolean.valueOf(bundle.getString("jedis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(bundle.getString("jedis.pool.testOnReturn")));

        // 根据配置实例化jedis池
        pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")),
                Integer.valueOf(strTimeout));
        dbIdx = Integer.valueOf(bundle.getString("redis.dbidx"));
        userTimeOut = Long.parseLong(bundle.getString("redis.userTimeOut"));
        sccTimeOut = Long.parseLong(bundle.getString("redis.sccTimeOut"));
        logicClassTimeOut = Long.parseLong(bundle.getString("redis.logicClassTimeOut"));
        // 创建多个redis共享服务

        /*
         * JedisShardInfo jedisShardInfo1 = new JedisShardInfo(
         * bundle.getString("redis.ip"),
         * Integer.valueOf(bundle.getString("redis.port"))); JedisShardInfo
         * jedisShardInfo2 = new JedisShardInfo( bundle.getString("redis.ip"),
         * Integer.valueOf(bundle.getString("redis.port")));
         *
         * List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
         * list.add(jedisShardInfo1); list.add(jedisShardInfo2);
         */

        // 根据配置文件,创建shared池实例
        // shardPool = new ShardedJedisPool(config, list);

    }

    //jedis池部分
    public static Jedis getResource() {
        Jedis jedis = pool.getResource();
        try {
            //Integer dbIdx = 0;
            jedis.select(dbIdx);
            return jedis;
        } catch(Exception e) {
            return jedis;
        }
    }

    public static Jedis getResource(Integer dbIdx) {
        Jedis jedis = pool.getResource();
        if(dbIdx==null) {
            dbIdx = 0;
        }
        jedis.select(dbIdx);
        return jedis;
    }

    public static Long getUserTimeOut() {
        return userTimeOut;
    }

    public static Long getSccTimeOut() {
        return sccTimeOut;
    }

    public static Long getLogicClassTimeOut() {
        return logicClassTimeOut;
    }

    public static void destory() {
        pool.destroy();
    }

    public static JedisPool getPool() {
        return pool;
    }
}
