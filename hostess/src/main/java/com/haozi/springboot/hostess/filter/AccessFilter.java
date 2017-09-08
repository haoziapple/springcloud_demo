package com.haozi.springboot.hostess.filter;

import com.haozi.springboot.hostess.filter.constants.FilterOrderMap;
import com.haozi.springboot.hostess.filter.constants.FilterType;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: com.haozi.springboot.hostess.filter.AccessFilter
 * @description: 权限校验过滤器
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/8/30 17:06
 **/
public class AccessFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

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
        logger.info("{} request to {}", request.getMethod(), request.getRequestURL().toString());

        // 目前先不做校验
        String accessToken = "accessToken";
        if (accessToken == null) {
            logger.warn("access token is empty");
            // 令zuul过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        logger.info("access token ok");
        return null;
    }
}
