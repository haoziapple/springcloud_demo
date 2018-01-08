package com.haozi.springboot.hostess.filter;

import com.haozi.springboot.hostess.filter.constants.FilterOrderMap;
import com.haozi.springboot.hostess.filter.constants.FilterType;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * @className: com.haozi.springboot.hostess.filter.ErrorFilter
 * @description: error类型过滤器，对所有过滤器中抛出的异常统一处理
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/8/30 17:23
 **/
public class ErrorFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return FilterType.ERROR;
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
        Throwable throwable = ctx.getThrowable();
        logger.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.set("error.exception", throwable.getCause());
        return null;
    }
}
