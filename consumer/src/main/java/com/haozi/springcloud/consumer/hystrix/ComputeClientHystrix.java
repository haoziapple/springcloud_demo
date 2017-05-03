package com.haozi.springcloud.consumer.hystrix;

import org.springframework.stereotype.Component;

import com.haozi.springcloud.consumer.intf.ComputeClient;

/**
 * @className:com.haozi.springcloud.consumer.hystrix.ComputeClientHystrix
 * @description:使用hystrix断路器，调用服务失败时的业务处理
 * @version:v1.0.0
 * @date:2017年5月3日 上午10:16:02
 * @author:WangHao
 */
@Component
public class ComputeClientHystrix implements ComputeClient
{

	@Override
	public Integer add(Integer a, Integer b)
	{
		return -9999;
	}

}
