package com.haozi.springcloud.provider2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haozi.springcloud.provider2.bean.ReqBean;
import com.haozi.springcloud.provider2.bean.RspBean;

/**
 * @className:com.haozi.springcloud.provider2.controller.Compute2Controller
 * @description:compute2暴露服务
 * @version:v1.0.0
 * @date:2017年5月8日 下午2:16:42
 * @author:WangHao
 */
@RestController
public class Compute2Controller
{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DiscoveryClient client;

	// GET方法暴露服务
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Integer add(@RequestParam Integer a, @RequestParam Integer b)
	{
		ServiceInstance instance = client.getLocalServiceInstance();
		Integer r = a + b;
		logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
		return r;
	}

	// POST方法暴露服务
	@RequestMapping(value = "/postReq", method = RequestMethod.POST)
	public RspBean postReq(@RequestBody ReqBean reqBean)
	{
		RspBean rsp = new RspBean();
		rsp.setRspCode("0");
		rsp.setRspMsg("ok");
		rsp.setId(reqBean.getId());

		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + rsp);
		return rsp;

	}
}
