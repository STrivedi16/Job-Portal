package com.JobPortal.interceptor;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ServiceInterceptor extends WebMvcConfigurerAdapter{

	Logger log=org.slf4j.LoggerFactory.getLogger(ServiceInterceptor.class);
	
	@Autowired
	private Interceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("Intercepter invoked...");
		
		registry.addInterceptor(interceptor);
	}
	
	
}
