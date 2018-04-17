package com.zheng.service;

import com.zheng.core.redis.MyJedisPool;
import com.zheng.dao.BlogDAO;
import com.zheng.entity.Blog;
import com.zheng.logic.BlogService;
import com.zheng.mapper.BlogMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BlogServiceTest {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogDAO blogDAO;

    @Autowired
    private BlogService blogService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() {
        Blog byUid = blogMapper.getByUid("1");
//        Blog byUid = new Blog();
        System.out.println(byUid);

        System.out.println(blogDAO.hello());
    }

    @Test
    public void getBlog() {
        Blog blog = blogService.getBlog();
        System.out.println(blog);
    }

    @Test
    public void testRedis(){
        Jedis jedis = MyJedisPool.getResource();
        jedis.set("a","a");
        jedis.close();
    }
}