package com.haozi.springcloud.provider2.bean;

/**
 * @className:com.haozi.springcloud.provider2.bean.ReqBean
 * @description:请求bean
 * @version:v1.0.0
 * @date:2017年5月8日 下午2:14:26
 * @author:WangHao
 */
public class ReqBean
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
		return "ReqBean [id=" + id + ", userKey=" + userKey + "]";
	}
}
