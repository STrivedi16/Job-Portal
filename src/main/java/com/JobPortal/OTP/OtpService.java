package com.JobPortal.OTP;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	
	@Autowired
	private OtpRepository repository;


	public static int generateOtp()
	{
		Random random=new Random();
		int max=9999;
		int min=1000;
		
		int otp=random.nextInt(max-min)+min;
		
		return otp;
	}
	
	public static int newOtp=OtpService.generateOtp();
	
	
	public  int OTP_VALIDATION_TIME=5*60*1000;
	
	
	public OtpEntity setOtpForVerify(String email)
	{
		OtpEntity entity=this.repository.findByEmailIgnoreCase(email);
		
		Date date=new Date();
		
		Timestamp timestamp=new Timestamp(date.getTime()+OTP_VALIDATION_TIME);
		
		if(entity==null)
		{
			OtpEntity entity2=new OtpEntity();
			entity2.setEmail(email);
			entity2.setOtp(newOtp);
			
			entity2.setOtpValidation(timestamp);
			
			return this.repository.save(entity2);
		}
		else {
			
			OtpEntity  setOtp=new OtpEntity();
			
			setOtp.setEmail(entity.getEmail());
			setOtp.setId(entity.getId());
			setOtp.setOtp(newOtp);
			setOtp.setOtpValidation(timestamp);
			
		return	this.repository.save(setOtp);
		}
		
	}
	
	
	public OtpEntity clearOtp(String email)
	{
		OtpEntity entity=this.repository.findByEmailIgnoreCase(email);
		
		entity.setCreationTime(null);
		entity.setOtp(0);
		entity.setOtpValidation(null);
		
		return this.repository.save(entity);
		
	}
	
	public OtpEntity veridyOtp(String email)
	{
		OtpEntity entity=this.repository.findByEmailIgnoreCase(email);
		
		return entity;
	}
}
