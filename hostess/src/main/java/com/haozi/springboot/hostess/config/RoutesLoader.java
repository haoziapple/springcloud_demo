package com.haozi.springboot.hostess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @className: com.haozi.springboot.hostess.config.RoutesLoader
 * @description: zuul网关路由信息
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/1 14:13
**/
@Component
public class RoutesLoader {
    @Autowired
    private ZuulProperties properties;

    public ZuulProperties getProperties() {
        return properties;
    }
}
