package com.JobPortal.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.JobPortal.Service.CustomerUserDetailsSerice;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	public final String PUBLIC_URL[]= {"/register","/login","/v3/api-docs"
			,"/v2/api-docs"
			,"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**",};	
	
	
	@Autowired
	private JwtEntryPoint entryPoint;
	
	@Autowired
	private JwtFilter filter;
	
	
	@Autowired
	private CustomerUserDetailsSerice customerUserDetailsSerice;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customerUserDetailsSerice);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().disable().csrf().disable()
		.authorizeRequests().antMatchers(PUBLIC_URL).permitAll()
		.anyRequest().authenticated().and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().exceptionHandling().authenticationEntryPoint(entryPoint);
		
		
		System.err.println("in web config");
		
		http.addFilterBefore(filter,  UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	public PasswordEncoder encoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception
	{
		return super.authenticationManager();
	}
	
}
