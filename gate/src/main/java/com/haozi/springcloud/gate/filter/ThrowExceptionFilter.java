package com.haozi.springcloud.gate.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ThrowExceptionFilter extends ZuulFilter
{
	private static Logger logger = LoggerFactory.getLogger(ThrowExceptionFilter.class);

	@Override
	public Object run()
	{
		RequestContext ctx = RequestContext.getCurrentContext();
		logger.info("This is a pre filter, it will throw a RuntimeException");
		// 这里不使用try catch的做法，使用error类型过滤器统一处理异常
		doSomething();
		return null;
	}

	@Override
	public boolean shouldFilter()
	{
		return true;
	}

	@Override
	public int filterOrder()
	{
		return 1;
	}

	@Override
	public String filterType()
	{
		return "pre";
	}

	private void doSomething()
	{
		throw new RuntimeException("Exist some errors...");
	}

}
