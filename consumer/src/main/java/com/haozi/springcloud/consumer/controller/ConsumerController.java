package com.haozi.springcloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.haozi.springcloud.consumer.intf.ComputeClient;

/**
 * @className:com.haozi.springcloud.consumer.controller.ConsumerController
 * @description:分别使用ribbon和feign两种不同的客户端方式(推荐使用feign)
 * @version:v1.0.0
 * @date:2017年5月8日 上午10:15:55
 * @author:WangHao
 */
@RestController
public class ConsumerController
{
	// 使用ribbon
	@Autowired
	RestTemplate restTemplate;

	// 使用feign
	@Autowired
	ComputeClient computeClient;

	// 使用ribbon
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add()
	{
		return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=20", String.class).getBody();
	}

	// 使用feign
	@RequestMapping(value = "/addUseFeign", method = RequestMethod.GET)
	public Integer addUseFeign()
	{
		return computeClient.add(20, 30);
	}
}
