package com.zheng.controller;

import com.zheng.base.page.OrderBean;
import com.zheng.base.page.OrderEnum;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import com.zheng.entity.Blog;
import com.zheng.entity.field.BlogConstants;
import com.zheng.logic.BlogService;
import com.zheng.model.BlogModel;
import com.zheng.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    //http://localhost:8080/

    @Autowired
    private BlogService blogService;

//    @RequestMapping("")
//    @ResponseBody
//    public String Test(ModelMap model) {
//        Blog blog = blogService.getBlog();
//        return blog.toString();
//    }

    //http://localhost:8080
    @RequestMapping("")
    public String getIndex(ModelMap modelMap) {
        Blog blog = blogService.getBlog();
        BlogModel blogModel = new BlogModel(blog,true);
        modelMap.addAttribute("blog", blogModel);

        PageList<BlogModel> blogModelPageList = blogService.listBlog(10, 1,true);
        modelMap.addAttribute("blogList", blogModelPageList);
        modelMap.addAttribute("page", 10);
        modelMap.addAttribute("pageNum", 10);
        return "index.html";
    }

    @RequestMapping("page/{pageNum}")
    public String getIndexByPage(String pageNum,ModelMap modelMap){
        Blog blog = blogService.getBlog();
        BlogModel blogModel = new BlogModel(blog,true);
        modelMap.addAttribute("blog", blogModel);
        modelMap.addAttribute("page", 10);
        modelMap.addAttribute("pageNum", 10);
        return "index.html";
    }

    @RequestMapping("blog/{id}")
    public String getBlog(@PathVariable String id, ModelMap modelMap) {
        if(StringUtil.isEmpty(id)){
            return null;
        }
        Blog blog = blogService.getBlogById(id);
        BlogModel blogModel = new BlogModel(blog,false);
        modelMap.addAttribute("blog", blogModel);
        return "single.html";
    }
}
