package com.JobPortal.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.JobPortal.Config.JwtTokenUtils;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Service.UserService;
import com.JobPortal.entity.UserEntity;

@Component
public class Interceptor implements HandlerInterceptor {

	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		String requestHeader=request.getHeader("Authorization");
		
		String token;
		if(requestHeader!=null && requestHeader.startsWith("Bearer "))
		{
			token=requestHeader.substring(7);
		
			String  username=this.jwtTokenUtils.getUsernameFromToken(token);
			
			UserEntity entity=this.repository.findByEmailIgnoreCase(username);
			
			ArrayList<SimpleGrantedAuthority> permissions=this.service.getAuthorities(entity.getId());
		
			 request.setAttribute("permissions", permissions);
		
		}
		
		
		return true;
		
		
		
		
		//return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	
}
