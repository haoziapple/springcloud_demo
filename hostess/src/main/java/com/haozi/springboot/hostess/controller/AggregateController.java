package com.haozi.springboot.hostess.controller;

import com.haozi.springboot.hostess.bean.RequestBean;
import com.haozi.springboot.hostess.common.RouteBean;
import com.haozi.springboot.hostess.common.RouteRsp;
import com.haozi.springboot.hostess.controller.constants.MappingValue;
import com.haozi.springboot.hostess.util.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @className: com.haozi.springboot.hostess.controller.AggregateController
 * @description: 聚合请求Controller
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/1 11:05
 **/
@Controller
@RequestMapping(MappingValue.Aggregrate.ROOT)
public class AggregateController implements EnvironmentAware {
    // 在请求头中保存被聚合请求的来源IP
    public static final String ORIGIN_HOST = "origin-host-from-aggregate";
    private static Logger logger = LoggerFactory.getLogger(AggregateController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AsyncRestTemplate asyncRestTemplate;
    // gateway自身的主机地址
    private String rootHost;

    public static void test(String[] args) {
        String path = "/a/**";
        System.out.println(path);

        String t = path.replace("**", "test");
        System.out.println("replace result is: " + t);
        String testAll = path.replaceAll("\\*\\*", "test");
        System.out.println("replaceAll result is: " + testAll);
    }

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
        // 获取原始请求IP
        String orginIp = IPUtil.getIpAddr(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());

        if (logger.isInfoEnabled())
            logger.info("receive aggregate request from {},request data is {}", orginIp, routeReq.toString());

        // 设置返回list
        List<RouteRsp> rspList = new ArrayList<>();
        for (RouteBean routeBean : routeReq) {
            // url需要以分隔符起始
            if (!routeBean.getUrl().startsWith("/"))
                routeBean.setUrl("/" + routeBean.getUrl());

            // 在请求头中保存被聚合请求的来源IP
            if (routeBean.getHeaderMap() == null)
                routeBean.setHeaderMap(new HashMap<String, String>());
            routeBean.getHeaderMap().put(ORIGIN_HOST, orginIp);

            // 初始化返回bean
            RouteRsp rsp = new RouteRsp();
            rsp.setRequestId(routeBean.getRequestId());
            rsp.setUrl(routeBean.getUrl());
            rspList.add(rsp);
        }

        // 发起请求
        List<ListenableFuture<ResponseEntity<String>>> futureList = this.sendAsyncReq(routeReq);
        // 获取返回
        this.getAsyncRsp(futureList, rspList);

        return rspList;
    }

    // 通过environment设置网关服务地址, 作为聚合请求时的根地址
    @Override
    public void setEnvironment(Environment environment) {
        this.rootHost = "http://" + environment.getProperty("server.address") + ":" + environment.getProperty("server.port");
    }

    // 异步发送批量请求
    private List<ListenableFuture<ResponseEntity<String>>> sendAsyncReq(List<RouteBean> routeReq) {
        List<ListenableFuture<ResponseEntity<String>>> futureList = new ArrayList<>();
        // 循环发起请求, 仍然请求本地的gateway，这样分发的请求可共用zuul的过滤器
        for (RouteBean routeBean : routeReq) {
            MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
            headerMap.setAll(routeBean.getHeaderMap());
            // 设置请求头与请求体
            HttpEntity<String> reqEntity = new HttpEntity<>(routeBean.getBody(), headerMap);
            ListenableFuture<ResponseEntity<String>> future;
            if (RequestMethod.GET.name().equalsIgnoreCase(routeBean.getReqMethod())) {
                // GET请求
                future = asyncRestTemplate.getForEntity(this.rootHost + routeBean.getUrl(), String.class);

            } else {
                // 默认post请求
                future = asyncRestTemplate.
                        postForEntity(this.rootHost + routeBean.getUrl(), reqEntity, String.class);
            }
            futureList.add(future);
        }
        return futureList;
    }

    // 获取异步批量请求的返回结果
    private void getAsyncRsp(List<ListenableFuture<ResponseEntity<String>>> futureList, List<RouteRsp> rspList) {
        for (int i = 0; i < rspList.size(); i++) {
            RouteRsp routeRsp = rspList.get(i);
            try {
                ResponseEntity<String> rspEntity = futureList.get(i).get();
                routeRsp.setHttpCode(rspEntity.getStatusCode().value());
                routeRsp.setBody(rspEntity.getBody());
                routeRsp.setHeaderMap(rspEntity.getHeaders().toSingleValueMap());
            } catch (Exception e) {
                logger.error("fail to get async http rsp", e);
                if (e.getCause() instanceof HttpClientErrorException)
                    routeRsp.setHttpCode(((HttpClientErrorException) e.getCause()).getStatusCode().value());
                else {
                    routeRsp.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    routeRsp.setBody(e.getMessage());
                }
            }
        }
    }
}
