package com.zheng.service.impl;

import com.zheng.base.BaseDAO;
import com.zheng.base.BaseServiceImpl;
import com.zheng.base.page.OrderBean;
import com.zheng.base.page.Page;
import com.zheng.dao.BlogDAO;
import com.zheng.entity.Blog;
import com.zheng.service.BlogService;
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
        return null;
    }
}