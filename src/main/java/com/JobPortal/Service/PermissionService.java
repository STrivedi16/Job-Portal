package com.JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.PermissionDto;
import com.JobPortal.Repository.PermissionsRepository;
import com.JobPortal.ServiceInterface.PermissionServiceImpl;
import com.JobPortal.entity.Permissions;

@Service
public class PermissionService implements PermissionServiceImpl{

	@Autowired
	private PermissionsRepository permissionsRepository;
	
	public PermissionDto setPermission(PermissionDto dto)
	{
		Permissions permissions=new  Permissions();
		
		permissions.setPermissions(dto.getPermission());
		
		this.permissionsRepository.save(permissions);
		
		return dto;
	}

	@Override
	public String deletePermission(long id) {
		
		this.permissionsRepository.deleteById(id);
		
		return "permission deleted";
	}
}
