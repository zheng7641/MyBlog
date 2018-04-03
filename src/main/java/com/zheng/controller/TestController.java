package com.zheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("test2")
public class TestController {

    @RequestMapping("test1")
    public String Test(ModelMap model) {
        System.out.println("test111");
        Map<String, String> result = new HashMap<String, String>();
        result.put("blog","blog1");
        model.addAllAttributes(result);
        return "index.html";

    }
}
