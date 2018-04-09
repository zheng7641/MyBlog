package com.zheng.entity;

public class Introduce {
    private Integer introduceid;

    private String content;

    public Integer getIntroduceid() {
        return introduceid;
    }

    public void setIntroduceid(Integer introduceid) {
        this.introduceid = introduceid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}