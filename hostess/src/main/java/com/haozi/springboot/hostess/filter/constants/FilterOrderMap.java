package com.haozi.springboot.hostess.filter.constants;

import com.haozi.springboot.hostess.filter.AccessFilter;
import com.haozi.springboot.hostess.filter.CountLimitFilter;
import com.haozi.springboot.hostess.filter.ErrorFilter;
import com.haozi.springboot.hostess.filter.LogFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: com.haozi.springboot.hostess.filter.constants.FilterOrderMap
 * @description: 过滤器顺序Map
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/8 9:24
 **/
public class FilterOrderMap {
    private static Map<String, Integer> map = new HashMap<>();

    static {
        // 权限filter
        map.put(AccessFilter.class.getName(), 0);
        // 日志filter
        map.put(LogFilter.class.getName(), 1);
        // 限流filter
        map.put(CountLimitFilter.class.getName(), 2);
        // 异常处理filter
        map.put(ErrorFilter.class.getName(), 10);
    }

    /**
     * @Description: 获取filter order
     * @param: [filterClassName]
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/9/8 9:33
     **/
    public static int getFilterOrder(String filterClassName)
    {
        return map.get(filterClassName);
    }

}
