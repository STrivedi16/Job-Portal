package com.JobPortal.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;

import io.swagger.annotations.AuthorizationScope;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER="Authorization";
	
	public ApiKey apiKey()
	{
		return new ApiKey("JWT", AUTHORIZATION_HEADER,"header");
	}
	
	private List<springfox.documentation.spi.service.contexts.SecurityContext> securityContexts()
	{
		return Arrays.asList(springfox.documentation.spi.service.contexts.SecurityContext.builder().securityReferences(securityReferences()).build());
	}
	
	
	private List<SecurityReference> securityReferences()
	{
		springfox.documentation.service.AuthorizationScope scope=new springfox.documentation.service.AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new springfox.documentation.service.AuthorizationScope[] {scope}));
	
	}
	
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKey()))
				.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
				
				
	}
	
	private ApiInfo getInfo() {
		return new ApiInfo("Job Portal", "this project is developed by Shubham Trivedi", "1.0","Trarms of service", new Contact("Shubham", "", "shubhammtrivedi@gmail.com"), "License of api", "Api licence Url", Collections.emptyList());

	}

	
}
