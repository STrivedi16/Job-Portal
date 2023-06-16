package com.JobPortal.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Config.JwtFilter;
import com.JobPortal.Config.JwtRefreshToken;
import com.JobPortal.Config.JwtTokenUtils;
import com.JobPortal.Model.JwtRequest;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Responce.SuccessMessageToken;
import com.JobPortal.Service.CustomerUserDetailsSerice;
import com.JobPortal.interceptor.Interceptor;


@RestController
public class JwtContoller {

	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private JwtFilter filter;
	
	@Autowired
	private JwtRefreshToken jwtRefreshToken;
	
	
	
	@Autowired
	private CustomerUserDetailsSerice customerUserDetailsSerice;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	



//	public String getPermission(HttpServletRequest request)
//	{
//		String listPermission=(String) request.getAttribute("permissions");
//	
//		
//	
//		return listPermission;
//	}
	
	public String token;
	
	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest)
	{
		System.out.println(jwtRequest);
		
		
		if(jwtRequest.getUsername().isEmpty() && jwtRequest.getPassword().isEmpty())
		{
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.INVALID_USERNAME_PASSWORD, ErrorMessageKey.USER_EO311OO),HttpStatus.BAD_REQUEST);
			
		}
		else if(jwtRequest.getUsername().isEmpty()==false && jwtRequest.getPassword().isEmpty()){
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.INVALID_USERNAME_PASSWORD, ErrorMessageKey.USER_EO311OO),HttpStatus.BAD_REQUEST);
			
		}
		else if(jwtRequest.getUsername().isEmpty() && jwtRequest.getPassword().isEmpty()==false){
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.INVALID_USERNAME_PASSWORD, ErrorMessageKey.USER_EO311OO),HttpStatus.BAD_REQUEST);
			
		}
		else {
	
		
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
			
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), ErrorMessageKey.USER_EO311OO),HttpStatus.BAD_REQUEST);
			
		}
		
		}
		UserDetails details=this.customerUserDetailsSerice.loadUserByUsername(jwtRequest.getUsername());
	
		
		
		 token=this.jwtTokenUtils.generateToken(details);
		
		String reftoken=this.jwtRefreshToken.generateReftoken(details);
		
		//ArrayList<String> permissions=this.customerUserDetailsSerice.getPermissionById(1);
		
		return new ResponseEntity<>(new SuccessMessageToken(SuccessMessageConstant.USER_LOGIN, SuccessMessageKey.USER_MO311OO, token, reftoken,null),HttpStatus.OK);
		
		
		}
	
	
}
