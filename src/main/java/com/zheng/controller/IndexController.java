package com.zheng.controller;

import com.zheng.entity.Blog;
import com.zheng.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
}
