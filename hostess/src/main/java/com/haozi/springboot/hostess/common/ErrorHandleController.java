package com.haozi.springboot.hostess.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @className: com.haozi.springboot.hostess.common.ErrorHandleController
 * @description: 异常处理Controller
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/6 14:53
 **/
@ControllerAdvice
public class ErrorHandleController {
    private static Logger logger = LoggerFactory.getLogger(ErrorHandleController.class);

    /**
     * @Description: 一般异常处理
     * @param: [rsp, e]
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/9/6 15:12
     **/
    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletResponse rsp, Exception e) {
        logger.error("Uncatched Controller Exception", e);
        rsp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
