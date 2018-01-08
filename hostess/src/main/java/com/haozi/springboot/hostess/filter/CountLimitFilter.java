package com.haozi.springboot.hostess.filter;

import com.haozi.springboot.hostess.filter.constants.FilterOrderMap;
import com.netflix.zuul.ZuulFilter;

/**
 * @className: com.haozi.springboot.hostess.filter.CountLimitFilter
 * @description: 网关限流过滤器 TODO 业务逻辑待实现
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/8 9:10
 **/
public class CountLimitFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return FilterOrderMap.getFilterOrder(this.getClass().getName());
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
