package com.haozi.springcloud.gate.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @className:com.haozi.springcloud.gate.filter.ErrorFilter
 * @description:error类型过滤器，对所有过滤器中抛出的异常统一处理
 * @version:v1.0.0
 * @date:2017年5月16日 下午2:58:40
 * @author:WangHao
 */
public class ErrorFilter extends ZuulFilter
{
	private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public boolean shouldFilter()
	{
		return true;
	}

	@Override
	public Object run()
	{
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		logger.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
		ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ctx.set("error.exception", throwable.getCause());
		return null;
	}

	@Override
	public String filterType()
	{
		return "error";
	}

	@Override
	public int filterOrder()
	{
		return 10;
	}

}
