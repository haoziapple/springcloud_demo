package com.haozi.springcloud.consumer.intf;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haozi.springcloud.consumer.hystrix.ComputeClientHystrix;

/**
 * @className:com.haozi.springcloud.consumer.intf.ComputeClient
 * @description:使用Feign客户端，以及Hystrix断路器
 * @version:v1.0.0
 * @date:2017年5月8日 上午10:14:49
 * @author:WangHao
 */
@FeignClient(value = "compute-service", fallback = ComputeClientHystrix.class)
public interface ComputeClient
{
	@RequestMapping(method = RequestMethod.GET, value = "/add")
	Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
