package com.haozi.springboot.hostess.common.log;

/**
 * @className: com.haozi.springboot.hostess.common.log.LogEntity
 * @description: 日志实体
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/6 17:46
**/
public class LogEntity {
    private String ip;

    private String requestId;

    private String className;

    private String methodName;

    private String paramData;

    private String rspData;

    private Long runtime;

    private String errorMsg;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getRspData() {
        return rspData;
    }

    public void setRspData(String rspData) {
        this.rspData = rspData;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "ip='" + ip + '\'' +
                ", requestId='" + requestId + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramData='" + paramData + '\'' +
                ", rspData='" + rspData + '\'' +
                ", runtime=" + runtime +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
