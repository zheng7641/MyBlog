package com.zheng.model;

import com.zheng.entity.Blog;
import com.zheng.common.util.DateUtil;
import com.zheng.common.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class BlogModel {
    /**
     * 博客主键
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 博客内容
     */
    private String content;

    /**
     * 博客标签
     */
    private String tagName;

    public BlogModel(){

    }

    public BlogModel(Blog blog){
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.createTime = DateUtil.toString(blog.getCreateTime(),null);
        this.updateTime = DateUtil.toString(blog.getUpdateTime(),null);
        this.content = blog.getContent();
        this.tagName = blog.getTagName();
    }

    public BlogModel(Blog blog,Boolean isShort){
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.createTime = DateUtil.toString(blog.getCreateTime(),null);
        this.updateTime = DateUtil.toString(blog.getUpdateTime(),null);
        if(isShort){
            if(StringUtil.isEmpty(blog.getIntroduction())){
                this.content = "";
            }else{
                this.content = blog.getIntroduction()+"...";
            }
        }else{
            this.content = blog.getContent();
        }
        this.tagName = blog.getTagName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "blogModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", content='" + content + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
