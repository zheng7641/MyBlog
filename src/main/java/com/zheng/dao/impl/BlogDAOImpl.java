package com.zheng.dao.impl;

import javax.annotation.Resource;

import com.zheng.base.page.OrderBean;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zheng.dao.BlogDAO;
import com.zheng.base.BaseDAOImpl;
import com.zheng.base.BaseMapper;
import com.zheng.mapper.BlogMapper;
import com.zheng.entity.Blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BlogDAOImpl extends BaseDAOImpl<Blog, String> implements BlogDAO {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public BaseMapper<Blog, String> getMapper() {
        return blogMapper;
    }

    @Override
    public String hello() {
        return "hellow";
    }

    @Override
    public PageList<Blog> searchBlog(String key, Page page) {
        List<Blog> blogs = blogMapper.searchBlog(key, page.getStartPosition(), page.getPageSize());
        page.setTotalCount(blogMapper.searchBlogCount(key));
        return new PageList<Blog>(blogs,page);
    }

    @Override
    public Integer deleteById(int id) {
        return blogMapper.deleteById(id);
    }
}
