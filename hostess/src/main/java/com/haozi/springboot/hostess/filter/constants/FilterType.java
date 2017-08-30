package com.haozi.springboot.hostess.filter.constants;

/**
 * @className: com.haozi.springboot.hostess.filter.constants.FilterType
 * @description: 路由类型常量类
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/8/30 17:20
**/
public class FilterType {
    private FilterType() {
    }

    // pre：可以在请求被路由之前调用, zuul默认类型
    public static final String PRE = "pre";
    // routing：在路由请求时候被调用
    public static final String ROUTING = "routing";
    // post：在routing和error过滤器之后被调用
    public static final String POST = "post";
    // error：处理请求时发生错误时被调用
    public static final String ERROR = "error";
}
