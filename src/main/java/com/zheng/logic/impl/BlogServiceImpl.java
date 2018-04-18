package com.zheng.logic.impl;

import com.zheng.base.BaseDAO;
import com.zheng.base.BaseServiceImpl;
import com.zheng.base.page.OrderBean;
import com.zheng.base.page.OrderEnum;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import com.zheng.dao.BlogDAO;
import com.zheng.entity.Blog;
import com.zheng.entity.field.BlogConstants;
import com.zheng.logic.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogServiceImpl extends BaseServiceImpl implements BlogService {
    @Resource
    private BlogDAO blogDAO;

    public BaseDAO<Blog, String> getBaseDAO() {
        return blogDAO;
    }

    public Blog getBlog(){
        Blog byUid = blogDAO.getByUid("1");
//        blogDAO.findByExample(new Blog(),new Page(),new OrderBean());
        return byUid;
    }

    public Blog getBlogById(String id){
        return blogDAO.getByUid(id);
    }

    @Override
    public PageList<Blog> listBlog(int pageSize, int currentPage) {
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        OrderBean ob = new OrderBean();
        ob.add(BlogConstants.CREATE_TIME, OrderEnum.DESC);
        PageList<Blog> blogList = blogDAO.findByExamplePage(new Blog(), page, ob);
        page.setTotalCount(blogDAO.getCount(new Blog()));
        blogList.setPage(page);
        return blogList;
    }
}