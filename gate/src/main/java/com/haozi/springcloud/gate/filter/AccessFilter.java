package com.haozi.springcloud.gate.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter
{
	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public Object run()
	{
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		Object accessToken = request.getParameter("accessToken");
		if (accessToken == null)
		{
			logger.warn("access token is empty");
			// 令zuul过滤该请求，不对其进行路由
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}
		logger.info("access token ok");
		return null;
	}

	// 返回一个boolean类型来判断该过滤器是否要执行
	@Override
	public boolean shouldFilter()
	{
		return true;
	}

	// 通过int值来定义过滤器的执行顺序
	@Override
	public int filterOrder()
	{
		return 0;
	}

	@Override
	public String filterType()
	{
		// pre：可以在请求被路由之前调用
		// routing：在路由请求时候被调用
		// post：在routing和error过滤器之后被调用
		// error：处理请求时发生错误时被调用
		return "pre";
	}

}
