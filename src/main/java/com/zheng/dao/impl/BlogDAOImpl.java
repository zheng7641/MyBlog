package com.zheng.dao.impl;

import com.zheng.dao.BlogDAO;
import com.zheng.entity.Blog;
import com.zheng.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BlogDAOImpl implements BlogDAO{

    @Autowired
    private BlogMapper blogMapper;

    public Blog getBlogById(int id) {
        return blogMapper.selectByPrimaryKey(id);
    }
}
