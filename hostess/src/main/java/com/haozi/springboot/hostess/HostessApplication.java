package com.haozi.springboot.hostess;

import com.haozi.springboot.hostess.filter.AccessFilter;
import com.haozi.springboot.hostess.filter.ErrorFilter;
import com.haozi.springboot.hostess.filter.LogFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


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
        ApplicationContext ctx = new SpringApplicationBuilder(HostessApplication.class).web(true).run(args);
        long end = System.currentTimeMillis();
        logger.info("hostess server start spent time: {} ms", end - start);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
//            if (beanName.contains("zuul") || beanName.contains("Zuul")) {
            logger.info("loaded bean's name: {}, class: {}", beanName, ctx.getBean(beanName).getClass().getName());
//            }
        }
        ZuulProperties prop = ctx.getBean(ZuulProperties.class);
        logger.info(prop.getRoutes().toString());
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public LogFilter logFilter() {
        return new LogFilter();
    }

}
