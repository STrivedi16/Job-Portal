package com.JobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Dto.RolePermissionDto;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Service.RolePermissionService;

@RestController
public class RolePermissionController {

	@Autowired
	private RolePermissionService service;

	@PostMapping("/rolePermission")
	@PreAuthorize("hasAuthority	('addRolePermission')")
	public ResponseEntity<?> setRolePermission(@RequestBody RolePermissionDto dto)
	{
		try {
			RolePermissionDto dto2=this.service.setRolePermission(dto);
		
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.ROLE_PERMISSION_ADDED, SuccessMessageKey.ROLE_PERMISSION_M031501, dto2),HttpStatus.OK);
		}
		catch (Exception e) {
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.ROLE_PERMISSION_NOT_STORED, SuccessMessageKey.ROLE_PERMISSION_M031501),HttpStatus.BAD_REQUEST);
		}
	}


}
