package com.haozi.springcloud.consumer.intf;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haozi.springcloud.consumer.hystrix.ComputeClient2Hystrix;

@FeignClient(value = "compute2-service", fallback = ComputeClient2Hystrix.class)
public interface Compute2Client
{
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);

	@RequestMapping(value = "/postReq", method = RequestMethod.POST)
	RspBeanClient postReq(@RequestBody ReqBeanClient reqBean);
}
