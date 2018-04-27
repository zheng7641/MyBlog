package com.zheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhengct on 2018/4/27
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("")
    public String getIndex(ModelMap modelMap) {

        return "admin/index.html";
    }

    @RequestMapping("/newUser")
    public String newUser(ModelMap modelMap) {

        return "admin/new-user.html";
    }
}
