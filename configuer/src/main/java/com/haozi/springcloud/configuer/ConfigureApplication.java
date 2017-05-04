package com.haozi.springcloud.configuer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigureApplication
{
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigureApplication.class).web(true).run(args);
	}
}
