package com.zheng.controller;

import com.zheng.entity.Blog;
import com.zheng.logic.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author zhengct on 2018/4/27
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("")
    public String getIndex(ModelMap modelMap) {

        return "admin/index.html";
    }

    @RequestMapping("/newUser")
    public String newUser(ModelMap modelMap) {

        return "admin/new-user.html";
    }

    @RequestMapping("/addBlog")
    @ResponseBody
    public String addBlog(String title,String blog,String introduction,ModelMap modelMap) {
        Blog entity = new Blog();
        entity.setContent(blog);
        entity.setTitle(title);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setIntroduction(introduction);
        entity.setTagName("aaa");
        blogService.addBlog(entity);
        return "success";
    }

    @RequestMapping("/getConfig")
    @ResponseBody
    public String getConfig(ModelMap modelMap) throws IOException {
        WritableResource res = new FileSystemResource("D:/JetBrains/workspace20180326/MyBlog/MyBlog/src/main/resources/ueditorConfig.json");
        InputStream ins = res.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int i;
        while ((i = ins.read()) != -1) {
            bos.write(i);
        }
//        System.out.println("读取的文件:" + res.getFilename() + ",内容:" + bos.toString());
        return bos.toString();
    }
}