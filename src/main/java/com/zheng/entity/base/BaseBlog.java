package com.zheng.entity.base;

import java.io.Serializable;

import com.zheng.base.BaseBean;

import java.util.Date;


public class BaseBlog extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 博客内容
     */
    private String content;
    private String tagName;


    /**
     * 博客主键
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 博客主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 博客内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 博客内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}