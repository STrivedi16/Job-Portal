package com.JobPortal.OTP;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Responce.SuccessMsg;
import com.JobPortal.entity.UserEntity;

@RestController
public class SendOtpController {

	@Autowired
	private OtpService otpService;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping("/forgot")
	public ResponseEntity<?> sentOtp(
			@RequestParam(name = "email",required = false)String email
			) throws MessagingException
	{	
		if(email.isEmpty())
		{
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_FOUND, ErrorMessageKey.USER_E031102),HttpStatus.BAD_REQUEST);
		}
		
		UserEntity entity=this.repository.findByEmailIgnoreCase(email);
		
		if(entity==null)
		{
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_FOUND, ErrorMessageKey.USER_E031102),HttpStatus.BAD_REQUEST);

		}
		
		
		String message="<h3>Hello "+ " "+entity.getName() +"</h3><br>"+"<h2><b> OTP"+" "+otpService.newOtp+"</b></h2> <br>"
				+"<p><h6>this otp is for vrtification </h6></p>"
				+"<p><h6>this is auto generated mail please do not replay on this mail</h6></p>";
		
		String subject="OTP for verification";
		String to=email;
		
		
		this.emailService.sendOtp(subject, message, to);
		
		this.otpService.setOtpForVerify(email);
		
		return new ResponseEntity<>(new SuccessMsg(SuccessMessageConstant.OTP_SENT,SuccessMessageKey.OTP_M031800),HttpStatus.OK);
	}
}
