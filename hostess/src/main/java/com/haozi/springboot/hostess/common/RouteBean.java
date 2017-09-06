package com.haozi.springboot.hostess.common;

import java.util.Map;

/**
 * @className: com.haozi.springboot.hostess.common.RouteBean
 * @description: 路由信息bean
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/5 16:11
**/
public class RouteBean {
    private String serviceId;

    private String url;

    private Map<String, String> headerMap;

    private String body;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "RouteBean{" +
                "serviceId='" + serviceId + '\'' +
                ", url='" + url + '\'' +
                ", headerMap=" + headerMap +
                ", body='" + body + '\'' +
                '}';
    }
}