package com.JobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Dto.PermissionDto;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Responce.SuccessMsg;
import com.JobPortal.Service.PermissionService;

@RestController
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PostMapping("/permission")
	@PreAuthorize("hasAuthority	('addPermission')")
	public ResponseEntity<?> setPermission(@RequestBody PermissionDto dto)
	{
		try {
			PermissionDto dto2=this.permissionService.setPermission(dto);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.PERMISSION_ADDED, SuccessMessageKey.PERMISSION_M031401, dto2),HttpStatus.OK);
			
			
		}
		catch (Exception e) {
		
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.PERMISSION_NOT_STORED, ErrorMessageKey.PERMISSION_E031401),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public ResponseEntity<?> deletePermission(@PathVariable("id") long id )
	{
		try{
			this.permissionService.deletePermission(id);
			
			return new ResponseEntity<>(new SuccessMsg(SuccessMessageConstant.PERMISSION_REMOVE, SuccessMessageKey.PERMISSION_M031402),HttpStatus.OK);
		}
		catch (ResourcesNotFoundException e) {
			
			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), ErrorMessageKey.PERMISSION_E031402),HttpStatus.BAD_REQUEST);
		}
		
	}
}
