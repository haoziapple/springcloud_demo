package com.haozi.springcloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.haozi.springcloud.consumer.intf.ComputeClient;

@RestController
public class ConsumerController
{
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ComputeClient computeClient;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add()
	{
		return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=20", String.class).getBody();
	}

	@RequestMapping(value = "/addUseFeign", method = RequestMethod.GET)
	public Integer addUseFeign()
	{
		return computeClient.add(20, 30);
	}
}
