package com.JobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Dto.RoleDto;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Service.RoleService;

@RestController
public class RolesController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping("/roles")
	@PreAuthorize("hasAuthority	('setRole')")
	public ResponseEntity<?> setRoles(@RequestBody RoleDto dto)
	{
		try {
			RoleDto dto2=this.roleService.setRole(dto);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.ROLE_ADD, SuccessMessageKey.ROLE_M031201, dto2),HttpStatus.OK);
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.ROLE_NOT_STORED, ErrorMessageKey.ROLE_E031201),HttpStatus.BAD_REQUEST);
		}
	}
	
}
