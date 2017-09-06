package com.haozi.springboot.hostess.util;

import com.google.gson.Gson;

/**
 * @className: com.haozi.springboot.hostess.util.JsonUtil
 * @description: json静态工具类
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/6 17:34
**/
public class JsonUtil {
    private JsonUtil() {
    }

    private static Gson gson = new Gson();

    public static String toJson(Object src)
    {
        return gson.toJson(src);
    }

}
