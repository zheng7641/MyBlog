package com.zheng.controller;

import com.zheng.entity.Blog;
import com.zheng.logic.BlogService;
import com.zheng.model.BlogModel;
import com.zheng.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {

    //http://localhost:8080/

    @Autowired
    private BlogService blogService;

    @RequestMapping("")
    @ResponseBody
    public String Test(ModelMap model) {
        Blog blog = blogService.getBlog();
        return blog.toString();
    }

    //http://localhost:8080/index
    @RequestMapping("index")
    public String getIndex(ModelMap modelMap) {
        Blog blog = blogService.getBlog();
        BlogModel blogModel = new BlogModel(blog,true);
        modelMap.addAttribute("blog", blogModel);
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
