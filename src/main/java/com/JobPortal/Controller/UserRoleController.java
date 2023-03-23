package com.JobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Dto.UserRoleDto;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Service.UserRoleService;

@RestController
public class UserRoleController {

	@Autowired
	private UserRoleService roleService;
	
	@PostMapping("/userRole")
	public ResponseEntity<?> setUserRole(@RequestBody UserRoleDto dto)
	{
		try {
			
			UserRoleDto dto2=this.roleService.setUserRole(dto);
		
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_ROLE, SuccessMessageKey.USER_ROLE_M031301, dto2),HttpStatus.OK);
		
		}
		catch (Exception e) {
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_ROLE_NOT_STORED, ErrorMessageKey.USER_ROLE_E031301),HttpStatus.BAD_REQUEST);
		}
	}
}
