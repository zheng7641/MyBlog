package com.zheng.controller;

import com.zheng.logic.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhengct on 2018/4/24
 */

@Controller
@RequestMapping("/")
public class SearchController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("")
    public String searchBlog(String key){

        return "index.html";
    }
}
