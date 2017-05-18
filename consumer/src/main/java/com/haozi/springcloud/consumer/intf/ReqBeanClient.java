package com.haozi.springcloud.consumer.intf;

/**
 * @className:com.haozi.springcloud.consumer.intf.ReqBeanClient
 * @description:客户端请求bean 
 * @version:v1.0.0 
 * @date:2017年5月17日 上午9:24:39
 * @author:WangHao
 */
public class ReqBeanClient
{
	private String id;

	private String userKey;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUserKey()
	{
		return userKey;
	}

	public void setUserKey(String userKey)
	{
		this.userKey = userKey;
	}

	@Override
	public String toString()
	{
		return "ReqBeanClient [id=" + id + ", userKey=" + userKey + "]";
	}
}
