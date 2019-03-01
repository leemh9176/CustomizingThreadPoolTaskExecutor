package com.example.demo.common.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.common.MyThreadPoolTaskExecutor;
import com.example.demo.common.aop.LogAspect;

@Configuration
public class ThreadProjectConfig {

	@Bean
	public Executor taskExecutor() {
		MyThreadPoolTaskExecutor executor = new MyThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.initialize();
		return executor;
	}
	
	@Bean
	public LogAspect LogAspect() {
		return new LogAspect();
	}
	
}
