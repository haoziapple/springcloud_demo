package com.haozi.springboot.hostess.controller;

import com.haozi.springboot.hostess.bean.RequestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

/**
 * @className: com.haozi.springboot.hostess.controller.AggregateController
 * @description: 聚合请求Controller
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/1 11:05
 **/
@Controller
@RequestMapping("/aggregate")
public class AggregateController {
    private static Logger logger = LoggerFactory.getLogger(AggregateController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @RequestMapping(value = "/test")
    @ResponseBody
    public RequestBean<String> test() {
        RequestBean<String> bean = new RequestBean<>();
        bean.setIp("ip");
        bean.setData("test");
        bean.setRequestId("requestId");
        bean.setTimestamp(System.currentTimeMillis());
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            logger.error("sleep interrupted", e);
        }
        return bean;
    }

    @RequestMapping(value = "/rest")
    public void testRest() {
        long start = System.currentTimeMillis();
        // run code
        ResponseEntity<RequestBean> bean = restTemplate.postForEntity("http://localhost:5555/aggregate/test", null, RequestBean.class);
        long end = System.currentTimeMillis();
        logger.info("run time: {} ms", end - start);
        logger.info("rest result: {}", bean.toString());
    }

    @RequestMapping("/asyncRest")
    public void testAsyncRest() {
        long start = System.currentTimeMillis();
        // run code
        ListenableFuture<ResponseEntity<RequestBean>> future = asyncRestTemplate.
                postForEntity("http://localhost:5555/aggregate/test", null, RequestBean.class);
        long end = System.currentTimeMillis();
        logger.info("run time: {} ms", end - start);
        String result = null;
        try {
            result = future.get().toString();
        } catch (InterruptedException | ExecutionException e) {
            logger.error("cannot get future result", e);
        }
        logger.info("asyncRest result: {}", result);
    }
}
