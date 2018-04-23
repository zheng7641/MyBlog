package com.zheng.controller;

import com.zheng.base.page.OrderBean;
import com.zheng.base.page.OrderEnum;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import com.zheng.entity.Blog;
import com.zheng.entity.Tag;
import com.zheng.entity.field.BlogConstants;
import com.zheng.logic.BlogService;
import com.zheng.logic.TagService;
import com.zheng.model.BlogModel;
import com.zheng.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    //http://localhost:8080/

    @Autowired
    private BlogService blogService;

    @Resource
    private TagService tagService;

//    @RequestMapping("")
//    @ResponseBody
//    public String Test(ModelMap model) {
//        Blog blog = blogService.getBlog();
//        return blog.toString();
//    }

    //http://localhost:8080
    @RequestMapping("")
    public String getIndex(ModelMap modelMap) {
        PageList<BlogModel> blogModelPageList = blogService.listBlog(5, 1,null,true);
        modelMap.addAttribute("blogList", blogModelPageList);
        modelMap.addAttribute("pageCount", blogModelPageList.getTotalPages());
        modelMap.addAttribute("currentPage", 1);

        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList",tagPageList);
        return "index.html";
    }

    @RequestMapping("page/{currentPage}")
    public String getIndexByPage(@PathVariable  int currentPage,ModelMap modelMap){
        PageList<BlogModel> blogModelPageList = blogService.listBlog(5, currentPage,null,true);
        modelMap.addAttribute("blogList", blogModelPageList);
        modelMap.addAttribute("pageCount", blogModelPageList.getTotalPages());
        modelMap.addAttribute("currentPage", currentPage);

        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList",tagPageList);
        return "index.html";
    }

    @RequestMapping("tag/{tagName}/{currentPage}")
    public String getIndexByTag(@PathVariable String tagName,@PathVariable String currentPage, ModelMap modelMap){

        Integer currentpage = 1;
        if(!StringUtil.isEmpty(currentPage)){
            currentpage = Integer.valueOf(currentPage);
        }
        PageList<BlogModel> blogModelPageList = blogService.listBlog(5, currentpage, tagName, true);
        modelMap.addAttribute("blogList",blogModelPageList);
        modelMap.addAttribute("pageCount", blogModelPageList.getTotalPages());
        modelMap.addAttribute("currentPage", currentPage);


        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList",tagPageList);
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

        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList",tagPageList);
        return "single.html";
    }
}
