package com.haozi.springcloud.useconfig;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UseConfigApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(UseConfigApplication.class).web(true).run(args);
	}

}
