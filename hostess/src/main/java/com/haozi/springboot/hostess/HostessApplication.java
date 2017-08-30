package com.haozi.springboot.hostess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @className: com.haozi.springboot.hostess.HostessApplication
 * @description: hostess gateway 启动类
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/8/30 16:45
**/
@EnableZuulProxy
@SpringCloudApplication
public class HostessApplication {
    
    private static Logger logger = LoggerFactory.getLogger(HostessApplication.class);
    /**
     * @Description: 启动main方法
     * @param: 
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/8/30 16:45
    **/
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // run code
        new SpringApplicationBuilder(HostessApplication.class).web(true).run(args);
        long end = System.currentTimeMillis();
        logger.info("hostess server start spent time: {} ms", end - start);

    }
}
