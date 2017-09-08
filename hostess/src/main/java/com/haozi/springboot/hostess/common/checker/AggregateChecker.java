package com.haozi.springboot.hostess.common.checker;

import com.haozi.springboot.hostess.bean.RequestBean;
import com.haozi.springboot.hostess.bean.RspBean;
import com.haozi.springboot.hostess.common.RouteBean;
import com.haozi.springboot.hostess.controller.constants.MappingValue;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @className: com.haozi.springboot.hostess.common.checker.AggregateChecker
 * @description: 聚合接口校验类
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/8 11:00
 **/
public class AggregateChecker {
    public static RspBean check(RequestBean<List<RouteBean>> requestBean, BindingResult bindingResult) {
        RspBean rspBean = ControllerChecker.check(requestBean, bindingResult);
        if (rspBean.getRspCode() != null)
            return rspBean;
        for (RouteBean routeBean : requestBean.getData()) {
            // TODO 要考虑url开头不带/的情况
            if (routeBean.getUrl().contains(MappingValue.Aggregrate.ROOT + MappingValue.Aggregrate.BATCH_REQ)) {

            }
        }
        return rspBean;
    }
}
