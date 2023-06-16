package com.JobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Config.JwtRefreshToken;
import com.JobPortal.Config.JwtTokenUtils;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Service.CustomerUserDetailsSerice;

@RestController
public class GenerateAccessToken {

	@Autowired
	private JwtTokenUtils tokenUtils;
	
	@Autowired
	private JwtRefreshToken jwtRefreshToken;
	
	@Autowired
	private CustomerUserDetailsSerice detailsSerice;
	
	@PostMapping("/accessToken")
	public ResponseEntity<?> generateToken(@RequestParam ("token") String token)
	{
		try {
			
			
			
			String username=this.jwtRefreshToken.getUsernameFromReftoken(token);
			
			UserDetails details=this.detailsSerice.loadUserByUsername(username);
			
			String accessToken=this.tokenUtils.generateToken(details);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.TOKEN_GENERATED, SuccessMessageKey.TOKEN_M032100, accessToken),HttpStatus.OK);
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), ErrorMessageKey.TOKEN_E032100),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
}
