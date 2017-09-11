package com.haozi.springboot.hostess.controller;

import com.haozi.springboot.hostess.bean.TestValidateBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @className: com.haozi.springboot.hostess.controller.ValidateController
 * @description: 测试校验Controller
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/11 9:54
 **/
@Controller
@RequestMapping("/validate")
public class ValidateController {
    private static Logger logger = LoggerFactory.getLogger(ValidateController.class);

    @RequestMapping("/test")
    public void test(@Valid TestValidateBean bean, BindingResult bindingResult) {
        logger.info("testValidateBean: {}", bean.toString());
        if (bindingResult.hasErrors()) {
            logger.info("get first err: {}", bindingResult.getFieldError().getDefaultMessage());
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError err : list) {
                logger.info("get the list ObjectError: {}", err.getDefaultMessage());
            }
            List<FieldError> list2 = bindingResult.getFieldErrors();
            for(FieldError err2:list2)
            {
                logger.info("get the list FieldError: {}", err2.getDefaultMessage());
            }
        }
    }

}
