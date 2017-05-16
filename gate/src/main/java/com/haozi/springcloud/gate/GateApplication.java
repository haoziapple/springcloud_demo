package com.haozi.springcloud.gate;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.haozi.springcloud.gate.filter.AccessFilter;
import com.haozi.springcloud.gate.filter.ErrorFilter;
import com.haozi.springcloud.gate.filter.ThrowExceptionFilter;

@EnableZuulProxy
@SpringCloudApplication
public class GateApplication
{
	public static void main(String[] args) {
		new SpringApplicationBuilder(GateApplication.class).web(true).run(args);
	}
	
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	
	@Bean
	public ThrowExceptionFilter throwExceptionFilter()
	{
		return new ThrowExceptionFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter()
	{
		return new ErrorFilter();
	}
}
