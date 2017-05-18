package com.haozi.springcloud.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.haozi.springcloud.consumer.intf.Compute2Client;
import com.haozi.springcloud.consumer.intf.ComputeClient;
import com.haozi.springcloud.consumer.intf.ReqBeanClient;
import com.haozi.springcloud.consumer.intf.RspBeanClient;

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
	private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);

	// 使用ribbon
	@Autowired
	RestTemplate restTemplate;

	// 使用feign
	@Autowired
	ComputeClient computeClient;
	@Autowired
	Compute2Client compute2Client;

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
		logger.info("addUseFeign");
		return computeClient.add(20, 30);
	}

	@RequestMapping(value = "/add2", method = RequestMethod.GET)
	public Integer add2(@RequestParam Integer a, @RequestParam Integer b)
	{
		logger.info("add2");
		return compute2Client.add(a, b);
	}

	@RequestMapping(value = "/postReq", method = RequestMethod.POST)
	public RspBeanClient postReq(@RequestBody ReqBeanClient reqBean)
	{
		logger.info("postReq {}", reqBean);
		return compute2Client.postReq(reqBean);
	}
}
