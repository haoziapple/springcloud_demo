package com.haozi.springcloud.admin;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * @className:com.haozi.springcloud.admin.CloudAdminApp
 * @description:spring-cloud-admin服务
 * @version:v1.0.0 
 * @date:2017年5月10日 下午4:42:31
 * @author:WangHao
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class CloudAdminApp
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(CloudAdminApp.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames)
		{
			System.out.println(beanName);
		}
	}
}
