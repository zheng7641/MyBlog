package com.zheng.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author zhengct on 2018/4/27
 */
@SuppressWarnings("deprecation")
public class HttpClientTest {

    public static void main(String[] args) throws Exception {
        //目标页面
        String url = "http://localhost:8080/";
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);

            CloseableHttpResponse response ;
            int a = 0;
            while (true) {
                response = client.execute(request);
                /**请求发送成功，并得到响应**/
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    /**读取服务器返回过来的json字符串数据**/
                    String strResult = EntityUtils.toString(response.getEntity());
                    a++;
                    System.out.println(strResult.substring(0,200));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
