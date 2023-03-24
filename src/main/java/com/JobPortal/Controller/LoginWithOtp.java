package com.JobPortal.Controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Config.JwtRefreshToken;
import com.JobPortal.Config.JwtTokenUtils;
import com.JobPortal.Dto.LoginDto;
import com.JobPortal.OTP.OtpEntity;
import com.JobPortal.OTP.OtpService;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Responce.SuccessMessageToken;
import com.JobPortal.Service.CustomerUserDetailsSerice;
import com.JobPortal.entity.UserEntity;

@RestController
public class LoginWithOtp {

	@Autowired
	private OtpService otpService;

	@Autowired
	private JwtRefreshToken refreshToken;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private CustomerUserDetailsSerice serice;
	
	@Autowired
	private UserRepository repository;

	@PostMapping("/loginWithOtp")
	public ResponseEntity<?> loginWithOtp(@RequestBody LoginDto dto)
	{
		try {
				OtpEntity entity=this.otpService.veridyOtp(dto.getUsername());
				
				Date date=new Date();
				Timestamp timestamp=new Timestamp(date.getTime());
				
				if(timestamp.compareTo(entity.getOtpValidation())==-1)
				{
					if(entity.getOtp()==dto.getOtp())
					{
						
						UserEntity entity2=this.repository.findByEmailIgnoreCase(entity.getEmail());
						
						UserDetails  details=this.serice.loadUserByUsername(entity2.getEmail());
					
						String token=this.jwtTokenUtils.generateToken(details);
					
						String reftoken=this.refreshToken.generateReftoken(details);
						
						this.otpService.clearOtp(entity.getEmail());
						
						return new ResponseEntity<>(new SuccessMessageToken(SuccessMessageConstant.USER_LOGIN, SuccessMessageKey.USER_MO311OO, token, reftoken),HttpStatus.OK);
					
					}
						
					else {
						throw new ResourcesNotFoundException();
					}
					
				}
				else {
					return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.LOGIN_FAIL, ErrorMessageKey.USER_EO311OO),HttpStatus.BAD_REQUEST);
				}
		}
		catch (ResourcesNotFoundException e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.LOGIN_FAIL, ErrorMessageKey.USER_EO311OO),HttpStatus.BAD_REQUEST);

		}
	}

}

