package com.haozi.springcloud.consumer.intf;

/**
 * @className:com.haozi.springcloud.consumer.intf.RspBeanClient
 * @description:客户端返回bean
 * @version:v1.0.0
 * @date:2017年5月17日 上午9:26:36
 * @author:WangHao
 */
public class RspBeanClient
{
	private String id;

	private String rspMsg;

	private String rspCode;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getRspMsg()
	{
		return rspMsg;
	}

	public void setRspMsg(String rspMsg)
	{
		this.rspMsg = rspMsg;
	}

	public String getRspCode()
	{
		return rspCode;
	}

	public void setRspCode(String rspCode)
	{
		this.rspCode = rspCode;
	}

	@Override
	public String toString()
	{
		return "RspBeanClient [id=" + id + ", rspMsg=" + rspMsg + ", rspCode=" + rspCode + "]";
	}
}
