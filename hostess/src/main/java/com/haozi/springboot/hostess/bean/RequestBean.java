package com.haozi.springboot.hostess.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.io.Serializable;


/**
 * @className: com.haozi.springboot.hostess.bean.RequestBean
 * @description: 一般请求bean
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/1 11:01
 **/
public class RequestBean<T> implements Serializable {
    // 具体数据信息
    @Valid
    private T data;
    // 请求Id
    @NotBlank(message = "requestId cannot be blank")
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
