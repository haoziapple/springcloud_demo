package com.haozi.springboot.hostess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: com.haozi.springboot.hostess.controller.ErrorController
 * @description: 异常controller--测试用
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/6 16:02
**/
@RequestMapping("/error")
@Controller
public class ErrorController {

    @RequestMapping("/common")
    public void commonError()
    {
        throw new RuntimeException("test common runtime Exception");
    }

}
