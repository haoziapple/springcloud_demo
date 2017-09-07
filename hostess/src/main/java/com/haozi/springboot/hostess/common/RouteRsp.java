package com.haozi.springboot.hostess.common;

import java.util.Map;

/**
 * @className: com.haozi.springboot.hostess.common.RouteRsp
 * @description: 路由请求返回
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/5 16:37
**/
public class RouteRsp {
    private String requestId;

    private String url;

    private Integer httpCode;

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

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
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
        return "RouteRsp{" +
                "requestId='" + requestId + '\'' +
                ", url='" + url + '\'' +
                ", httpCode=" + httpCode +
                ", headerMap=" + headerMap +
                ", body='" + body + '\'' +
                '}';
    }
}
