package com.zheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.zheng.util.IpUtil.getIp;

@Controller
@RequestMapping("/about")
public class AboutController {

    @RequestMapping("")
    public String getAbout(ModelMap modelMap){
        modelMap.addAttribute("rootPath",getIp());
        return "about.html";
    }
}
