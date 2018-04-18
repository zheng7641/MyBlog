package com.zheng.logic;

import com.zheng.entity.Blog;
import com.zheng.base.BaseService;

public interface BlogService extends BaseService {

    public Blog getBlog();

    public Blog getBlogById(String id);
}
