package com.haozi.springboot.hostess.common.checker;

import com.haozi.springboot.hostess.bean.RequestBean;
import com.haozi.springboot.hostess.bean.RspBean;
import com.haozi.springboot.hostess.common.RouteBean;
import com.haozi.springboot.hostess.controller.constants.MappingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @className: com.haozi.springboot.hostess.common.checker.AggregateChecker
 * @description: 聚合接口校验类
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/8 11:00
 **/
public class AggregateChecker {
    private static Logger logger = LoggerFactory.getLogger(AggregateChecker.class);

    public static RspBean check(RequestBean<List<RouteBean>> requestBean, BindingResult bindingResult) {
        RspBean rspBean = ControllerChecker.check(requestBean, bindingResult);
        if (rspBean.getRspCode() != null)
            return rspBean;
        for (RouteBean routeBean : requestBean.getData()) {
            // 考虑url开头不带/的情况
            if (routeBean.getUrl().startsWith(MappingValue.Aggregrate.ROOT + MappingValue.Aggregrate.BATCH_REQ)
                    || ("/" + routeBean.getUrl()).startsWith(MappingValue.Aggregrate.ROOT + MappingValue.Aggregrate.BATCH_REQ)) {
                logger.error("aggregate request contains interable url, requestId: {}, illegal routeBean: {}", requestBean.getRequestId(), routeBean.toString());
                // 设置返回bean
                rspBean.setRequestId(requestBean.getRequestId());
                rspBean.setRspCode(HttpStatus.BAD_REQUEST.toString());
                rspBean.setRspMsg("aggregate request contains interable url: " + routeBean.toString());
                return rspBean;
            }
        }
        return rspBean;
    }
}
