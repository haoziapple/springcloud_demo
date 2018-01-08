package com.haozi.springboot.hostess.common;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * @className: com.haozi.springboot.hostess.common.RouteBean
 * @description: 路由信息bean
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/5 16:11
 **/
public class RouteBean {
    @NotBlank(message = "requestId cannot be blank")
    private String requestId;

    @NotBlank(message = "requestMethod cannot be blank(GET or POST)")
    private String requestMethod;

    @NotBlank(message = "url cannot be blank")
    private String url;

    private Map<String, String> headerMap;

    private String body;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "RouteBean{" +
                "requestId='" + requestId + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", url='" + url + '\'' +
                ", headerMap=" + headerMap +
                ", body='" + body + '\'' +
                '}';
    }
}
