package com.JobPortal.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Service.CustomerUserDetailsSerice;
import com.JobPortal.entity.UserEntity;
import com.JobPortal.entity.UserRolesEntity;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenUtils  jwtTokenUtils;
	
	@Autowired
	private JwtRefreshToken jwtRefreshToken;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private CustomerUserDetailsSerice serice;
	
	public static long id;
	
	public static String username=null;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestHeader=request.getHeader("Authorization");
		
		 
		
		String jwtToken;
		
		String type=null;
		
		
		if(requestHeader!=null && requestHeader.startsWith("Bearer "))
		{
			//jwtToken=requestHeader.substring(7);
			jwtToken = requestHeader.split(" ")[1].trim();
			
			try {
					type=this.jwtTokenUtils.getTypeFromToken(jwtToken);
					
					
					if(type.equals("Access"))
					{
						
						username=this.jwtTokenUtils.getUsernameFromToken(jwtToken);
						
						UserEntity entity=this.repository.findByEmailIgnoreCase(username);
						
						id=entity.getId();
						
						
					}
					else {
						System.out.println("Error");
					}
			}
			catch (Exception e) {
				new ResponseEntity<>("Invalid Token",HttpStatus.UNAUTHORIZED);
			}
		}
		
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null && type.equals("Access"))
		{
			UserDetails details=this.serice.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(details,null ,details.getAuthorities());
		
			upat.setDetails(new WebAuthenticationDetailsSource());
			
			SecurityContextHolder.getContext().setAuthentication(upat);
		
		}
		else {
			System.out.println("User not valid");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
