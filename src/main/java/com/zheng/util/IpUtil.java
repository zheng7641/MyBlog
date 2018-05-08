package com.zheng.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhengct on 2018/5/7
 */
public class IpUtil {
    public static String getIp(){
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            return "http://"+addr.getHostAddress()+":8080";
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
