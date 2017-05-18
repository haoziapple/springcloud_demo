package com.haozi.springcloud.consumer.hystrix;

import org.springframework.stereotype.Component;

import com.haozi.springcloud.consumer.intf.Compute2Client;
import com.haozi.springcloud.consumer.intf.ReqBeanClient;
import com.haozi.springcloud.consumer.intf.RspBeanClient;

@Component
public class ComputeClient2Hystrix implements Compute2Client
{

	@Override
	public Integer add(Integer a, Integer b)
	{
		return -999;
	}

	@Override
	public RspBeanClient postReq(ReqBeanClient reqBean)
	{
		return new RspBeanClient();
	}

}
