package com.zheng.service.impl.pachong;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.geom.RoundRectangle2D;
import java.util.List;

/**
 * @author zhengct on 2018/5/26
 */
public class FangZi {
    private String url;
    private String name;
    private String price;
    private String location;
    private List<String> juli;
    private String mianji;
    private String fangxing;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getJuli() {
        return juli;
    }

    public void setJuli(List<String> juli) {
        this.juli = juli;
    }

    public String getMianji() {
        return mianji;
    }

    public void setMianji(String mianji) {
        this.mianji = mianji;
    }

    public String getFangxing() {
        return fangxing;
    }

    public void setFangxing(String fangxing) {
        this.fangxing = fangxing;
    }

    @Override
    public String toString() {
        return new ToStringBuilder("")
                .append("price", price)
                .append("mianji", mianji)
                .append("bi", Double.valueOf(Double.valueOf(price)/Double.valueOf(mianji)).intValue())
                .append("fangxing", fangxing)
                .append("name", name)
                .append("location", location)
                .append("juli", juli)
                .append("url", url)
                .toString();
    }
}
