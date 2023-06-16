package com.JobPortal.Config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAsync
@EnableTransactionManagement
public class AsyncConfig {

	@Bean
	public Executor executor()
	{
		ThreadPoolTaskExecutor poolTaskExecutor=new ThreadPoolTaskExecutor();
		poolTaskExecutor.setCorePoolSize(2);
		poolTaskExecutor.setMaxPoolSize(2);
		poolTaskExecutor.setQueueCapacity(100);
		poolTaskExecutor.setThreadNamePrefix("userThread-");
		poolTaskExecutor.initialize();
		return poolTaskExecutor;
		
	}
	
}
