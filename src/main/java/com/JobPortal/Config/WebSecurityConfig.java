package com.JobPortal.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.JobPortal.Service.CustomerUserDetailsSerice;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{

	
//	public final static String PUBLIC_URL[]= {"/user/register","/login","/v3/api-docs"
//			,"/v2/api-docs"
//			,"/swagger-resources/**",
//			"/swagger-ui/**",
//			"/user/forgotpassword/{email}",
//			"/webjars/**","/forgot","/loginWithOtp","/SamlResponse","/req/recruter"};	
//	
//	
	
	
	//  /user/register,/login,/v3/api-docs,/v2/api-docs,/swagger-resources/**,/swagger-ui/**,/user/forgotpassword/{email},/webjars/**,/forgot,/loginWithOtp,/req/recruter
	
	
	@Value("${spring.security.ignored}")
	private  String [] public_urls;
	
	@Autowired
	private JwtEntryPoint entryPoint;
	
	@Autowired
	private JwtFilter filter;
	
	
	@Autowired
	private CustomerUserDetailsSerice customerUserDetailsSerice;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.authenticationProvider(authenticationProvider());
//		// this is method used for configure the authentication manager which is responsible 
//		// for user authentication and in this method you can define how user details will be
//		// retrived and how the authentication process is handle
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.cors().disable().csrf().disable()
//		
//		//CORS - no cors means  website would not be able to use
//		// resource from another server apart from their own
//		
//		// csrf - it is type of attack that where malicious site(Harmfull) send unauthorized request 
//		// on behalf of user who is already authenticated 
//		
//		.authorizeRequests().antMatchers(public_urls).permitAll()
//		
//		// this is simply say that which api is accessible without token 
//		
//		.anyRequest().authenticated()//.and().httpBasic()
//		
//		// any other request apart from this public url will authenticated 
//		
//		// http basic -- it simply just basic authentication 
//		
//		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		
//		// why session creation policy is stateless - 
//		// becaues server dose not need to manage and stored session information for each user
//		
//		.and().exceptionHandling().authenticationEntryPoint(entryPoint);
//		
//		// it is used for handling authentiction exception
//		
//		System.err.println("in web config");
//		
//		http.addFilterBefore(filter,  UsernamePasswordAuthenticationFilter.class);
//	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.cors().disable().csrf().disable()
		
		//CORS - no cors means  website would not be able to use
		// resource from another server apart from their own
		
		// csrf - it is type of attack that where malicious site(Harmfull) send unauthorized request 
		// on behalf of user who is already authenticated 
		.authorizeRequests().antMatchers(public_urls).permitAll().anyRequest().authenticated()
		
		// this line give which api is public or which api has accessible publicly and any other request is 
		// authenticated
		
		
		.and().httpBasic()
		
		// http basic - it consider as basic authentication in appication 
		
		
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		
		// session creation policy - Stateless - that server will not store and mange session  for each 
		// and every user 
		
		.and().exceptionHandling().authenticationEntryPoint(entryPoint);
		
		// it is used to handle authentication exception 
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		// this authentication is apply to each api 
		
		
		http.authenticationProvider(authenticationProvider());
		
		return   http.build();
		
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        
        // Daoauthenticationproider  is class that implements authentication provider 
        // it is used to authenticate user with its username and password 
        
        authenticationProvider.setUserDetailsService(customerUserDetailsSerice);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
//	 @Bean
//	 public AuthenticationManager authenticationManagerBean() throws Exception {
//	     return super.authenticationManagerBean();
//	 }


	 @Bean
		public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
			return configuration.getAuthenticationManager();
		}

	
	
}
