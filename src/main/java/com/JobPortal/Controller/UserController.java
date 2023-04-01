package com.JobPortal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Config.JwtFilter;
import com.JobPortal.Config.PasswordVerification;
import com.JobPortal.Dto.UserDto;
import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserProfileMerge;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Service.UserService;
import com.JobPortal.entity.UserEntity;

@RestController
public class UserController {

	@Autowired
	private UserService  service;
	
	@Autowired
	private PasswordVerification passwordVerification;
	
	@Autowired
	private JwtFilter filter;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody  UserDto dto)
	{
		boolean password=this.passwordVerification.verifyPassword(dto.getPassword());
		
		if(password==true && dto.getEmail().isEmpty()==false)
		{
		
		try {
			
			
			
			UserDto dto2=this.service.registerUser(dto);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_REGISTER, SuccessMessageKey.USER_M031101, dto2),HttpStatus.OK);
		}
		catch (Exception e) {
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_REGISTER, ErrorMessageKey.USER_E031101),HttpStatus.BAD_REQUEST);
		}
		}
		else {
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_REGISTER, ErrorMessageKey.USER_E031101),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserData(@PathVariable("id") long id)
	{
		
		try {
			
			
			
			if(filter.id==id)
			{
			
			//List<UserInterface> list=this.service.getUserDetails(id);
			List<UserInterface> list=this.service.getUserDetailsProfile(id);
			
			
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_DETAILS, SuccessMessageKey.USER_M031102, list),HttpStatus.OK);
		}
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.ACCESS_DENIED, ErrorMessageKey.ACCESS_DENIED),HttpStatus.BAD_REQUEST);
		}
		catch (ResourcesNotFoundException e) {
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_FOUND, ErrorMessageKey.USER_E031102),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority	('showUser')")
	public ResponseEntity<?> getUsersDetails(@PathVariable("id") long id)
	{
		
		try {
			
			List<UserInterface> list=this.service.getUserDetails(id);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_DETAILS, SuccessMessageKey.USER_M031102, list),HttpStatus.OK);
		}
		catch (ResourcesNotFoundException e) {
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_FOUND, ErrorMessageKey.USER_E031102),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasAuthority	('showUser')")
	public ResponseEntity<?> getAllUsers(
			@RequestParam(name = "pagenumber",defaultValue = "0",required = false)Integer pagenumber,
			@RequestParam(name = "pagenumber",defaultValue = "5",required = false)Integer pagesize
			)
	{
		
		try {
			
			List<UserInterface> list=this.service.getAll(pagesize, pagenumber);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_DETAILS, SuccessMessageKey.USER_M031102, list),HttpStatus.OK);
		}
		catch (ResourcesNotFoundException e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_FOUND, ErrorMessageKey.USER_E031102),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	@PatchMapping("/forgotpassword/{email}")
	public ResponseEntity<?> forgotPassword(@PathVariable("email") String email,@RequestBody UserDto dto) throws Exception
	{
		try {
			String password=this.service.forgotPassword(email, dto);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_PASSWROD_UPDATED, SuccessMessageKey.USER_M031103, password),HttpStatus.OK);
		}
		catch (ResourcesNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_PASSWROD_NOT_UPDATED, ErrorMessageKey.USER_E031103),HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
