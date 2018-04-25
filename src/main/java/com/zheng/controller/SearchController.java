package com.zheng.controller;

import com.zheng.base.page.PageList;
import com.zheng.logic.BlogService;
import com.zheng.model.BlogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhengct on 2018/4/24
 */

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private BlogService blogService;


}
