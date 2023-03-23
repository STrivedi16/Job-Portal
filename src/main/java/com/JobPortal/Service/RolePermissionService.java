package com.JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.RolePermissionDto;
import com.JobPortal.Repository.PermissionsRepository;
import com.JobPortal.Repository.RolePermissionsRepository;
import com.JobPortal.Repository.RoleRepository;
import com.JobPortal.entity.Permissions;
import com.JobPortal.entity.Roles;
import com.JobPortal.entity.RolesPermissionEntity;

@Service
public class RolePermissionService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionsRepository permissionsRepository;
		
	@Autowired
	private RolePermissionsRepository repository;

	public RolePermissionDto setRolePermission(RolePermissionDto dto) throws Exception
	{
		Roles roles=this.roleRepository.findById(dto.getRoleId()).orElseThrow(()-> new Exception("role not found"));
	
		Permissions permissions=this.permissionsRepository.findById(dto.getPermissionId()).orElseThrow(()-> new Exception("permission not found"));
	
		RolesPermissionEntity entity=new RolesPermissionEntity();
		entity.setRoleEntity(roles);
		entity.setPermissions(permissions);
		
		this.repository.save(entity);
		
		return dto;
	}



}
