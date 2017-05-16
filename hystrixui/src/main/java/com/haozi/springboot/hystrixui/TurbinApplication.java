package com.haozi.springboot.hystrixui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class TurbinApplication
{
	public static void main(String[] args)
	{

		new SpringApplicationBuilder(TurbinApplication.class).web(true).run(args);
	}
}
