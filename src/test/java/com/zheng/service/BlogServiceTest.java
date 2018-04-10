package com.zheng.service;

import com.zheng.dao.BlogDAO;
import com.zheng.entity.Blog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BlogServiceTest {

    @Autowired
    private BlogDAO blogDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() {
        Blog byUid = blogDao.getByUid("1");
        System.out.println(byUid);
    }
}