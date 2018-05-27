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

import java.util.*;


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
        String[] dizhis = {"北苑路北","立水桥南","立水桥","大屯路东","惠新西街北口","惠新西街南口",
                "和平西桥","和平里北街","雍和宫","安贞门","芍药居","太阳宫","三元桥","亮马桥"};
        String basePath = "https://m.lianjia.com";

        //所有房子页面网站
//        List<Map<String, String>> pathList = new ArrayList<>();
        Map<String,String> pathList = new HashMap<>();
//        List<String> pathList = new ArrayList<>();
        for (String dizhi : dizhis) {
            Document document = Jsoup.connect("https://m.lianjia.com/bj/zufang/rs" + dizhi + "/").get();
            Elements aList = document.getElementsByTag("ul");
            for (Element elementLi : aList) {
                Elements provinceEl = elementLi.getElementsByTag("li");
                for (Element element : provinceEl) {
                    String categroyName = element.text();
                    Element el = element.select("a").first();
                    String url = el != null ? el.attr("href") : "";
                    pathList.put(dizhi+"---"+basePath+url,"");
                }
            }
        }

        System.out.println("1");
        List<FangZi> fangziList = new ArrayList<>();
        int a = 0;
        for (String path : pathList.keySet()) {
//            System.out.println(a++ + "size" + pathList.size());
            String key = path.split("---")[0];
            String allPath = path.split("---")[1];
            Document document = Jsoup.connect(allPath).get();

            FangZi fangzi = new FangZi();

            String price = document.getElementsByAttributeValue("data-mark", "price").text();
            fangzi.setPrice(price);

            try {
                String zufang = allPath.split("zufang")[1];
                fangzi.setUrl("https://bj.lianjia.com/zufang" + zufang);
            }catch (Exception e){
                fangzi.setUrl(allPath);
            }

            Elements house_desc = document.getElementsByClass("house_desc");
            fangzi.setName(house_desc.first() == null ? "" : house_desc.first().text().toString());

            Elements mianji = document.getElementsByAttributeValue("class", "red big");

            if (!mianji.isEmpty() && mianji.get(1) != null) {
                fangzi.setFangxing(mianji.get(1).text());
            }

            if (!mianji.isEmpty() && mianji.get(2) != null) {
                fangzi.setMianji(mianji.get(2).text());
            }

            Elements location = document.getElementsByAttributeValue("class", "marker_title");
            fangzi.setLocation(location.text());

            if (fangzi.getName() != null && fangzi.getName() != "") {
                fangzi.setMianji(fangzi.getMianji().replace("m²", ""));
                if (Double.valueOf(fangzi.getPrice()) < 8000) {
                    fangziList.add(fangzi);
//                    System.out.println(fangzi);
                }
            }
        }

        Collections.sort(fangziList, new Comparator<FangZi>() {
            public int compare(FangZi o1, FangZi o2) {
                double rank1 = 0;
                double rank2 =0;
                double mianji1 = Double.valueOf(o1.getMianji());
                double mianji2 = Double.valueOf(o2.getMianji());

                double price1 =  Double.valueOf(o1.getPrice());
                double price2 = Double.valueOf(o2.getPrice());

                rank1 = price1/mianji1;
                rank2 = price2/mianji2;

                if(rank1>rank2){
                    return 1;
                }else{
                    return -1;
                }
            }
        });


        for(FangZi fangzi:fangziList){
            System.out.println(fangzi);
        }
        System.out.println("");
    }
}
