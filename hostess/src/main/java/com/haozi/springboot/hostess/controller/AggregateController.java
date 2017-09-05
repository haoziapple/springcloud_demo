package com.haozi.springboot.hostess.controller;

import com.haozi.springboot.hostess.bean.RequestBean;
import com.haozi.springboot.hostess.common.RouteBean;
import com.haozi.springboot.hostess.common.RouteRsp;
import com.haozi.springboot.hostess.controller.constants.MappingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @className: com.haozi.springboot.hostess.controller.AggregateController
 * @description: 聚合请求Controller
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/1 11:05
 **/
@Controller
@RequestMapping(MappingValue.Aggregrate.ROOT)
public class AggregateController {
    private static Logger logger = LoggerFactory.getLogger(AggregateController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    // zuul配置，用以获取路由信息
    @Autowired
    private ZuulProperties zuulProp;

    @Autowired
    private Environment env;

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
        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.
                postForEntity("http://localhost:5555/aggregate/test", null, String.class);
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

    /**
     * @Description: 批量提交请求，提供接口聚合功能
     * @param: []
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/9/5 16:45
     **/
    @RequestMapping(value = MappingValue.Aggregrate.BATCH_REQ, method = RequestMethod.POST)
    @ResponseBody
    public List<RouteRsp> batchReq(@RequestBody List<RouteBean> routeReq) {
        logger.info("get req, {}", routeReq.toString());

        // 设置返回list
        List<RouteRsp> rspList = new ArrayList<>();
        for (RouteBean routeBean : routeReq) {
            RouteRsp rsp = new RouteRsp();
            rsp.setServcieId(routeBean.getServiceId());
            rsp.setUrl(routeBean.getUrl());
            rspList.add(rsp);
        }

        // 发起请求
        List<ListenableFuture<ResponseEntity<String>>> futureList = this.sendAsyncReq(routeReq);
        // 获取返回
        this.getAsyncRsp(futureList, rspList);

        return rspList;
    }

    // 异步发送批量请求
    private List<ListenableFuture<ResponseEntity<String>>> sendAsyncReq(List<RouteBean> routeReq) {
        List<ListenableFuture<ResponseEntity<String>>> futureList = new ArrayList<>();

        // 循环发起请求, 仍然请求本地的gateway，这样分发的请求可共用zuul的过滤器
        // TODO 传递分发请求的头信息，否则使用默认头将造成头信息丢失
        for (RouteBean routeBean : routeReq) {
            Map<String, ZuulProperties.ZuulRoute> routeMap = zuulProp.getRoutes();
            String subPath = routeMap.get(routeBean.getServiceId()).getPath().replace("**", routeBean.getUrl());
            ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.
                    postForEntity(this.getRootHost() + subPath, null, String.class);
            futureList.add(future);
        }
        return futureList;
    }

    // 获取异步批量请求的返回结果
    private void getAsyncRsp(List<ListenableFuture<ResponseEntity<String>>> futureList, List<RouteRsp> rspList) {
        for (int i = 0; i < rspList.size(); i++) {
            try {
                ResponseEntity<String> rspEntity = futureList.get(i).get();
                RouteRsp routeRsp = rspList.get(i);
                routeRsp.setHttpCode(rspEntity.getStatusCode().value());
                routeRsp.setBody(rspEntity.getBody());
                routeRsp.setHeaderMap(rspEntity.getHeaders().toSingleValueMap());
            } catch (Exception e) {
                logger.error("fail to get async http rsp", e);
            }
        }
    }

    // 获取gateway的请求地址
    // TODO 待优化，初始化的时候获取地址，提高效率
    private String getRootHost() {
        return "http://" + env.getProperty("server.address") + ":" + env.getProperty("server.port");
    }

    public static void main(String[] args) {
        String path = "/a/**";
        System.out.println(path);

        String t = path.replace("**", "test");
        System.out.println(t);
    }
}
