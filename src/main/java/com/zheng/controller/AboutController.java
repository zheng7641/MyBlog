package com.zheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {

    @RequestMapping("")
    public String getAbout(){
        return "about.html";
    }
}
