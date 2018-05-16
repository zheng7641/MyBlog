package com.zheng.model;

import com.zheng.common.util.DateUtil;
import com.zheng.common.util.StringUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @author zhengct on 2018/5/14
 */
public class ActionInfo {
    //访问url
    private String url;
    //用户ip
    private String ip;
    //当前页面码
    private String viewCode;
    //后继页面码
    private String nextViewCode;
    //当前分页值
    private Integer viewPage;
    //后继分页值
    private Integer nextViewPage;
    //操作分类
    private String optType;
    //操作码
    private String optCode;
    //操作时间
    private Date optDate;

    //浏览器名称
    private String browserName;
    //浏览器版本
    private String browserVer;
    //操作系统名称
    private String osName;
    //操作系统版本
    private String osVersion;
    //设备类型
    private String client;
    //生产厂家
    private String manufacturer;
    //手机型号
    private String phone;

    private String SPLIT_STR = "\t";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getViewCode() {
        return viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getNextViewCode() {
        return nextViewCode;
    }

    public void setNextViewCode(String nextViewCode) {
        this.nextViewCode = nextViewCode;
    }

    public Integer getViewPage() {
        return viewPage;
    }

    public void setViewPage(Integer viewPage) {
        this.viewPage = viewPage;
    }

    public Integer getNextViewPage() {
        return nextViewPage;
    }

    public void setNextViewPage(Integer nextViewPage) {
        this.nextViewPage = nextViewPage;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserVer() {
        return browserVer;
    }

    public void setBrowserVer(String browserVer) {
        this.browserVer = browserVer;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("url:").append( StringUtil.checkString(url,false,"")).append(SPLIT_STR)
                .append("ip:").append( StringUtil.checkString(ip,false,"")).append(SPLIT_STR)
                .append("viewCode:").append( StringUtil.checkString(viewCode,false,"")).append(SPLIT_STR)
                .append("nextViewCode:").append( StringUtil.checkString(nextViewCode,false,"")).append(SPLIT_STR)
                .append("viewPage:").append( viewPage==null?"":viewPage).append(SPLIT_STR)
                .append("nextViewPage:").append( nextViewPage==null?"":nextViewPage).append(SPLIT_STR)
                .append("optType:").append( StringUtil.checkString(optType,false,"")).append(SPLIT_STR)
                .append("optCode:").append( StringUtil.checkString(optCode,false,"")).append(SPLIT_STR)
                .append("optDate:").append( DateUtil.toString(optDate,"yyyy-MM-dd hh:mm:ss.SSS")).append(SPLIT_STR)
                .append("browserName:").append( StringUtil.checkString(browserName,false,"")).append(SPLIT_STR)
                .append("browserVer:").append( StringUtil.checkString(browserVer,false,"")).append(SPLIT_STR)
                .append("osName:").append( StringUtil.checkString(osName,false,"")).append(SPLIT_STR)
                .append("osVersion:").append( StringUtil.checkString(osVersion,false,"")).append(SPLIT_STR)
                .append("client:").append( StringUtil.checkString(client,false,"")).append(SPLIT_STR)
                .append("manufacturer:").append( StringUtil.checkString(manufacturer,false,"")).append(SPLIT_STR)
                .append("phone:").append( StringUtil.checkString(phone,false,""))
                .toString();
    }
}
