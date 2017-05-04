package com.haozi.springcloud.provider2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ComputeService2Application
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(ComputeService2Application.class).web(true).run(args);
	}
}
