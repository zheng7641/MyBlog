package com.zheng.dao;

import com.zheng.base.BaseDAO;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import com.zheng.entity.Blog;

import java.util.List;

public interface BlogDAO extends BaseDAO<Blog, String> {
    public String hello();
    public PageList<Blog> searchBlog(String key, Page page);
}
