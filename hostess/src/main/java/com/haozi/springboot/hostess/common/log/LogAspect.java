package com.haozi.springboot.hostess.common.log;

import com.google.gson.Gson;
import com.haozi.springboot.hostess.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @className: com.haozi.springboot.hostess.common.log.LogAspect
 * @description: 日志记录Aspect
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/6 17:00
 **/
@Aspect
@Component
public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // controller切面
    // 必须是controller包下，以Controller为结尾的类的public方法
    @Pointcut(value = "execution(public * com.haozi.springboot.hostess.controller..*Controller.*(..))")
    public void controllerPointCut() {
    }

    /**
     * @Description: 使用环绕通知打印日志，若有异常抛出给#ErrorHandlerController进行捕获
     * @param: [jointPoint]
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/9/6 18:14
     **/
    @Around(value = "controllerPointCut()")
    public Object aroudAdvice(ProceedingJoinPoint jointPoint) throws Throwable {
        LogEntity logEntity = new LogEntity();
        logEntity.setClassName(jointPoint.getSignature().getDeclaringTypeName());
        logEntity.setMethodName(jointPoint.getSignature().getName());
        logEntity.setParamData(JsonUtil.toJson(jointPoint.getArgs()));
        long start = System.currentTimeMillis();
        Object retVal = null;
        try {
            retVal = jointPoint.proceed();
        } catch (Throwable throwable) {
            logEntity.setErrorMsg(throwable.getMessage());
            takeLog(logEntity, start);
            throw throwable;
        }

        logEntity.setRspData(JsonUtil.toJson(retVal));
        takeLog(logEntity, start);
        return retVal;
    }

    private void takeLog(LogEntity logEntity, long start) {
        long end = System.currentTimeMillis();
        logEntity.setRuntime(end - start);
        if (logger.isInfoEnabled()) {
            logger.info("take controller log: {}", JsonUtil.toJson(logEntity));
        }
    }
}
