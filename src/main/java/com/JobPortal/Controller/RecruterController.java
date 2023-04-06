package com.JobPortal.Controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Config.JwtFilter;

import com.JobPortal.Dto.RecruterDto;
import com.JobPortal.Dto.RecruterStatusDto;
import com.JobPortal.OTP.EmailService;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Service.RecrutorService;
import com.JobPortal.entity.CompanyStatus;
import com.JobPortal.entity.RecruterEntity;
import com.netflix.discovery.converters.Auto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/req")
public class RecruterController {

	@Autowired
	private RecrutorService recrutorService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private JwtFilter filter;
	
	@PostMapping("/recruter")
	public ResponseEntity<?> setRecruter(@org.springframework.web.bind.annotation.RequestBody RecruterDto dto)
	{
		try {
			
			System.out.println(dto.getCompanyName());
			System.out.println(dto.getEmail());
			
			RecruterDto dto2=this.recrutorService.setDetails(dto);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.RECRUTER_ADD, SuccessMessageKey.RECRUTER_M032001, dto),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.RECRUTER_NOT_STORED,ErrorMessageKey.RECRUITER_E031901),HttpStatus.BAD_REQUEST);
		}
	
	}
	//helper.addattchement
	
	@PatchMapping("/recruiter/{id}")
	@PreAuthorize("hasAuthority	('recruiterStatus')")
	public ResponseEntity<?> updateStatus(@PathVariable("id") long id , @RequestBody RecruterStatusDto  dto) throws MessagingException 
	{
		try {
			
			RecruterEntity entity=this.recrutorService.updateStatus(id, dto);
			
			
			if(entity.getStatus().equals(CompanyStatus.REGISTER))
			{
				String message="<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<head>\r\n"
						+ "    <title>Recruiter Account Registration Confirmation</title>\r\n"
						+ "    <style>\r\n"
						+ "        body {\r\n"
						+ "            font-family: Arial, sans-serif;\r\n"
						+ "            background-color: #f8f8f8;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .container {\r\n"
						+ "            max-width: 600px;\r\n"
						+ "            margin: 0 auto;\r\n"
						+ "            padding: 20px;\r\n"
						+ "            background-color: #fff;\r\n"
						+ "            border-radius: 5px;\r\n"
						+ "            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        h1 {\r\n"
						+ "            font-size: 24px;\r\n"
						+ "            margin-bottom: 20px;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        p {\r\n"
						+ "            font-size: 16px;\r\n"
						+ "            line-height: 1.5;\r\n"
						+ "            margin-bottom: 10px;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .footer {\r\n"
						+ "            font-size: 14px;\r\n"
						+ "            color: #777;\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <div class=\"container\">\r\n"
						+ "        <h1>Recruiter Account Registration Confirmation</h1>\r\n"
						+ "        <p>\r\n"
						+ "            Dear "+entity.getName()+",\r\n"
						+ "        </p>\r\n"
						+ "        <p>\r\n"
						+ "            Congratulations! Your account has been successfully registered in our system. You can now log in with your credentials and start using our recruitment platform to find and connect with potential candidates.\r\n"
						+ "        </p>\r\n"
						+ "        <p>\r\n"
						+ "            Thank you for choosing our service.\r\n"
						+ "        </p>\r\n"
						+ "        <p class=\"footer\">\r\n"
						+ "            Sincerely,\r\n"
						+ "            <br>\r\n"
						+ "            "+filter.username+"\r\n"
						+ "        </p>\r\n"
						+ "    </div>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "";
				
				String subject="Recruiter Registration Update";
				
				String to=entity.getCompanyEmail();
				
				this.emailService.sendOtp(subject, message, to);
				
			}
			
			else {
				String message="<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<head>\r\n"
						+ "    <title>Account Confirmation Rejection</title>\r\n"
						+ "    <style>\r\n"
						+ "        body {\r\n"
						+ "            font-family: Arial, sans-serif;\r\n"
						+ "            background-color: #f8f8f8;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .container {\r\n"
						+ "            max-width: 600px;\r\n"
						+ "            margin: 0 auto;\r\n"
						+ "            padding: 20px;\r\n"
						+ "            background-color: #fff;\r\n"
						+ "            border-radius: 5px;\r\n"
						+ "            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        h1 {\r\n"
						+ "            font-size: 24px;\r\n"
						+ "            margin-bottom: 20px;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        p {\r\n"
						+ "            font-size: 16px;\r\n"
						+ "            line-height: 1.5;\r\n"
						+ "            margin-bottom: 10px;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .footer {\r\n"
						+ "            font-size: 14px;\r\n"
						+ "            color: #777;\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <div class=\"container\">\r\n"
						+ "        <h1>Account Confirmation Rejection</h1>\r\n"
						+ "        <p>\r\n"
						+ "            Dear [User's Name],\r\n"
						+ "        </p>\r\n"
						+ "        <p>\r\n"
						+ "            We regret to inform you that your account confirmation has been rejected. If you have any questions or concerns, please contact our support team for further assistance.\r\n"
						+ "        </p>\r\n"
						+ "        <p>\r\n"
						+ "            Thank you.\r\n"
						+ "        </p>\r\n"
						+ "        <p class=\"footer\">\r\n"
						+ "            Sincerely,\r\n"
						+ "            <br>\r\n"
						+ "            [Your Company Name]\r\n"
						+ "        </p>\r\n"
						+ "    </div>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "";
				
				String subject="Recruiter Registration Update";
				
				String to=entity.getCompanyEmail();
				
				this.emailService.sendOtp(subject, message, to);
			}
			
			
			
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.RECRUTER_STATUS,SuccessMessageKey.RECRUTER_M032002, entity),HttpStatus.OK);
		}
		catch (ResourcesNotFoundException e) {
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.RECRUTER_STATUS_NOT_UPDATE, ErrorMessageKey.RECRUITER_E031902),HttpStatus.BAD_REQUEST);
		}
	}
	
}
