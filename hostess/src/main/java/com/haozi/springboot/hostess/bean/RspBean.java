package com.haozi.springboot.hostess.bean;

import java.io.Serializable;

/**
 * @className: com.haozi.springboot.hostess.bean.RspBean
 * @description: 一般返回bean
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/5 16:27
**/
public class RspBean<T> implements Serializable {
    // 具体数据信息
    private T data;
    // 请求Id
    private String requestId;
    // 时间戳-服务端
    private Long timestamp;
    // 返回码
    private String rspCode;
    // 返回消息
    private String rspMsg;
    // 错误日志
    private String errLog;

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

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public String getErrLog() {
        return errLog;
    }

    public void setErrLog(String errLog) {
        this.errLog = errLog;
    }

    @Override
    public String toString() {
        return "RspBean{" +
                "data=" + data +
                ", requestId='" + requestId + '\'' +
                ", timestamp=" + timestamp +
                ", rspCode='" + rspCode + '\'' +
                ", rspMsg='" + rspMsg + '\'' +
                ", errLog='" + errLog + '\'' +
                '}';
    }
}
