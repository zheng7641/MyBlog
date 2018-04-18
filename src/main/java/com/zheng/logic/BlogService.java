package com.zheng.logic;

import com.zheng.base.BaseService;
import com.zheng.base.page.OrderBean;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import com.zheng.entity.Blog;

public interface BlogService extends BaseService {

    public Blog getBlog();

    public Blog getBlogById(String id);

    public PageList<Blog> listBlog(int pageSize,int currentPage);
}
