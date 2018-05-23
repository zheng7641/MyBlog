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
import com.zheng.model.BlogModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl extends BaseServiceImpl implements BlogService {
    @Resource
    private BlogDAO blogDAO;

    public BaseDAO<Blog, String> getBaseDAO() {
        return blogDAO;
    }

    private static Logger logger = Logger.getLogger(BlogServiceImpl.class.getName());

    public Blog getBlog() {
        Blog byUid = blogDAO.getByUid("1");
//        blogDAO.findByExample(new Blog(),new Page(),new OrderBean());
        return byUid;
    }

    public Blog getBlogById(String id) {
        return blogDAO.getByUid(id);
    }

    @Override
    public PageList<BlogModel> listBlog(int pageSize, int currentPage, String tagName, boolean isShort) {
        OrderBean ob = new OrderBean();
        ob.add(BlogConstants.CREATE_TIME, OrderEnum.DESC);

        Page page = new Page();
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        page.setTotalCount(blogDAO.getCount(new Blog()));

        Blog entity = new Blog();
        entity.setTagName(tagName);
        entity.setIsDelete(0l);
        PageList<Blog> blogList = blogDAO.findByExamplePage(entity, page, ob);
        blogList.setPage(page);

        List<BlogModel> blogModelList = new ArrayList<BlogModel>();
        for (Blog blog : blogList.getDatalist()) {
            blogModelList.add(new BlogModel(blog, isShort));
        }
        PageList<BlogModel> blogModelPageList = new PageList<>(blogModelList, page);
        return blogModelPageList;

    }

    @Override
    public PageList<BlogModel> searchBlog(String key, int pageSize, int currentPage, boolean isShort) {
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        PageList<Blog> blogPageList = blogDAO.searchBlog(key, page);
        List<BlogModel> blogModelList = new ArrayList<BlogModel>();
        for (Blog blog : blogPageList.getDatalist()) {
            blogModelList.add(new BlogModel(blog, isShort));
        }
        page.setTotalCount(blogPageList.getPage().getTotalCount());
        PageList<BlogModel> blogModelPageList = new PageList<>(blogModelList, page);
        return blogModelPageList;
    }

    @Override
    public int addBlog(Blog blog) {
        return blogDAO.insert(blog);
    }

    public Integer deleteBlog(int id){
        return  blogDAO.deleteById(id);
    }
}