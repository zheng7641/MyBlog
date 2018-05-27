package com.zheng.service.impl.pachong;

import org.apache.avro.generic.GenericData;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhengct on 2018/4/27
 */
@SuppressWarnings("deprecation")
public class PachongClientTest {
    public static String encodeUnicode(String str) {
        int iValue = 0;
        String uStr = "";
        for (int i = 0; i < str.length(); i++) {
            iValue = (int) str.charAt(i);
            if (iValue <= 256) {
                uStr += str.charAt(i);
            } else {
                uStr += "\\u" + Integer.toHexString(iValue);
            }
        }
        return uStr;
    }

    public static void main(String[] args) throws Exception {
        String[] dizhis = {"惠新西街南口", "大屯路东", "北苑路北", "立水桥南", "立水桥", "天通苑南", "天通苑"};
        String basePath = "https://m.lianjia.com";

        //所有房子页面网站
        List<String> pathList = new ArrayList<>();
        for (String dizhi : dizhis) {
            Document document = Jsoup.connect("https://m.lianjia.com/bj/zufang/rs" + dizhi + "/").get();
            Elements aList = document.getElementsByTag("ul");
            for (Element elementLi : aList) {
                Elements provinceEl = elementLi.getElementsByTag("li");
                for (Element element : provinceEl) {
                    String categroyName = element.text();
                    Element el = element.select("a").first();
                    String url = el != null ? el.attr("href") : "";
                    pathList.add(basePath+url);
                }
            }
        }
        System.out.println("");
    }
}
