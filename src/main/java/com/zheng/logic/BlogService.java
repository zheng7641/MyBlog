package com.zheng.logic;

import com.zheng.base.BaseService;
import com.zheng.base.page.OrderBean;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import com.zheng.entity.Blog;
import com.zheng.model.BlogModel;

public interface BlogService extends BaseService {

    public Blog getBlog();

    public Blog getBlogById(String id);

    public PageList<BlogModel> listBlog(int pageSize, int currentPage, String tagName,boolean isShort);
}
