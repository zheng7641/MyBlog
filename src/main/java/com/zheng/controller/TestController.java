package com.zheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test2")
public class TestController {

    @RequestMapping("test1")
    public String Test() {
        System.out.println("test111");
        return "index.html";

    }
}
