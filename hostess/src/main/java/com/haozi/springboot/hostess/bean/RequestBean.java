package com.haozi.springboot.hostess.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @className: com.haozi.springboot.hostess.bean.RequestBean
 * @description: 一般请求bean
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/1 11:01
 **/
@XmlRootElement
public class RequestBean<T> implements Serializable {
    // 具体数据信息
    private T data;
    // 请求Id
    private String requestId;
    // 时间戳-客户端
    private Long timestamp;
    // ip地址
    private String ip;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "RequestBean{" +
                "data=" + data +
                ", requestId='" + requestId + '\'' +
                ", timestamp=" + timestamp +
                ", ip='" + ip + '\'' +
                '}';
    }
}
