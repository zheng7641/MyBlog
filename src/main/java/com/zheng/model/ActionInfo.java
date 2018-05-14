package com.zheng.model;

/**
 * @author zhengct on 2018/5/14
 */
public class ActionInfo {
    private String ip;
    private String url;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ActionInfo{" +
                "ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
