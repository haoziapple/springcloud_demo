package com.haozi.springcloud.provider2.bean;

/**
 * @className:com.haozi.springcloud.provider2.bean.RspBean
 * @description:返回bean
 * @version:v1.0.0
 * @date:2017年5月8日 下午2:12:27
 * @author:WangHao
 */
public class RspBean
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
		return "RspBean [id=" + id + ", rspMsg=" + rspMsg + ", rspCode=" + rspCode + "]";
	}
}
