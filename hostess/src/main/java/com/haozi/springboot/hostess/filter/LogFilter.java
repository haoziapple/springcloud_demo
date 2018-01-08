package com.haozi.springboot.hostess.filter;

import com.haozi.springboot.hostess.controller.AggregateController;
import com.haozi.springboot.hostess.filter.constants.FilterOrderMap;
import com.haozi.springboot.hostess.filter.constants.FilterType;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: com.haozi.springboot.hostess.filter.LogFilter
 * @description: 日志记录过滤器
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/6 18:37
 **/
public class LogFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public String filterType() {
        return FilterType.PRE;
    }

    @Override
    public int filterOrder() {
        return FilterOrderMap.getFilterOrder(this.getClass().getName());
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (logger.isInfoEnabled()) {
            logger.info("method: {}, url: {}, URI: {}, parameterMap: {}, remoteAddr: {}, remoteHost: {}", request.getMethod(), request.getRequestURL().toString(), request.getRequestURI(), request.getParameterMap(), request.getRemoteAddr(), request.getRemoteHost());
            if (request.getHeader(AggregateController.ORIGIN_HOST) != null)
                logger.info("this request is from hostess aggregate, the original host is: {}", request.getHeader(AggregateController.ORIGIN_HOST));
        }
        return null;
    }
}
