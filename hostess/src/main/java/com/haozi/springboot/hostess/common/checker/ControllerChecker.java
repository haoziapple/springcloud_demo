package com.haozi.springboot.hostess.common.checker;

import com.haozi.springboot.hostess.bean.RequestBean;
import com.haozi.springboot.hostess.bean.RspBean;
import com.haozi.springboot.hostess.common.RouteRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @className: com.haozi.springboot.hostess.common.checker.ControllerChecker
 * @description: 一般的接口校验类
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/8 11:04
 **/
public class ControllerChecker {
    private static Logger logger = LoggerFactory.getLogger(ControllerChecker.class);

    public static RspBean check(RequestBean requestBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("parameter error: {}", bindingResult.getFieldError().getDefaultMessage());
            // 设置返回bean
            RspBean rspBean = new RspBean<>();
            rspBean.setRequestId(requestBean.getRequestId());
            rspBean.setRspCode(HttpStatus.BAD_REQUEST.toString());
            rspBean.setRspMsg(bindingResult.getFieldError().getDefaultMessage());
            return rspBean;
        } else {
            return new RspBean<>();
        }
    }
}
