package com.zheng.util;

/**
 * @author zhengct on 2018/5/7
 */
public class ValidationUtil {

    private static int MIN_INT_VALUE = 0;

    private static int MAX_INT_VALUE = Integer.MAX_VALUE;

    public static boolean validationInteger(String value) {
        Integer result = 0;
        if (isNull(value)) {
            return false;
        }
        try {
            result = Integer.valueOf(value);
        } catch (Exception e) {
            return false;
        }
        if (!isScope(result)) {
            return false;
        }
        return true;
    }

    private static boolean isNull(Object o) {
        if (o == null) return true;
        return false;
    }

    private static boolean isScope(Integer i) {
        if (i < MIN_INT_VALUE || i > MAX_INT_VALUE) {
            return false;
        }
        return true;
    }
}
