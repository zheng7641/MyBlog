package com.zheng.controller;

import com.zheng.base.page.PageList;
import com.zheng.entity.Blog;
import com.zheng.entity.Tag;
import com.zheng.logic.BlogService;
import com.zheng.logic.TagService;
import com.zheng.model.BlogModel;
import com.zheng.common.util.StringUtil;
import com.zheng.common.util.ValidationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static com.zheng.common.util.IpUtil.getIp;

@Controller
@RequestMapping("/")
public class IndexController {

    //http://localhost:8080/

    @Autowired
    private BlogService blogService;

    @Resource
    private TagService tagService;

    @RequestMapping("/test")
    public String Test(ModelMap model) {
        return "test.html";
    }

    private static Logger logger = LogManager.getLogger(IndexController.class.getName());

    //http://localhost:8080
    @RequestMapping("")
    public String getIndex(ModelMap modelMap) {
        PageList<BlogModel> blogModelPageList = blogService.listBlog(5, 1, null, true);
        modelMap.addAttribute("blogList", blogModelPageList);
        modelMap.addAttribute("pageCount", blogModelPageList.getTotalPages());
        modelMap.addAttribute("currentPage", 1);

        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList", tagPageList);

        modelMap.addAttribute("rootPath", getIp());
        logger.debug("getIndex");
        return "index.html";
    }

    @RequestMapping("page/{currentPage}")
    public String getIndexByPage(@PathVariable String currentPage, ModelMap modelMap) {
        if (!ValidationUtil.validationInteger(currentPage)) {
            return "404.html";
        }
        PageList<BlogModel> blogModelPageList = blogService.listBlog(5, Integer.valueOf(currentPage), null, true);
        modelMap.addAttribute("blogList", blogModelPageList);
        modelMap.addAttribute("pageCount", blogModelPageList.getTotalPages());
        modelMap.addAttribute("currentPage", currentPage);

        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList", tagPageList);

        modelMap.addAttribute("rootPath", getIp());
        return "index.html";
    }

    @RequestMapping("tag/{tagName}/{currentPage}")
    public String getIndexByTag(@PathVariable String tagName, @PathVariable String currentPage, ModelMap modelMap) {
        if (!ValidationUtil.validationInteger(currentPage)) {
            return "404.html";
        }
        Integer currentpage = 1;
        if (!StringUtil.isEmpty(currentPage)) {
            currentpage = Integer.valueOf(currentPage);
        }
        PageList<BlogModel> blogModelPageList = blogService.listBlog(5, currentpage, tagName, true);
        modelMap.addAttribute("blogList", blogModelPageList);
        modelMap.addAttribute("pageCount", blogModelPageList.getTotalPages());
        modelMap.addAttribute("currentPage", currentPage);

        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList", tagPageList);

        modelMap.addAttribute("rootPath", getIp());
        return "index.html";
    }

    @RequestMapping("/search/{key}")
    public String searchBlog(@PathVariable String key, ModelMap modelMap) {
        PageList<BlogModel> blogModelPageList = blogService.searchBlog(key, 6, 1, true);

        modelMap.addAttribute("blogList", blogModelPageList);
        modelMap.addAttribute("pageCount", blogModelPageList.getTotalPages());
        modelMap.addAttribute("currentPage", 1);

        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList", tagPageList);
        return "index.html";
    }

    @RequestMapping("blog/{id}")
    public String getBlog(@PathVariable String id, ModelMap modelMap) {
        if (!ValidationUtil.validationInteger(id)) {
            return "404.html";
        }
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return "404.html";
        }
        BlogModel blogModel = new BlogModel(blog, false);
        modelMap.addAttribute("blog", blogModel);

        PageList<Tag> tagPageList = tagService.listTag(5, 1);
        modelMap.addAttribute("tagList", tagPageList);

        modelMap.addAttribute("rootPath", getIp());
        return "single.html";
    }

}
