package com.JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.RoleDto;
import com.JobPortal.Repository.RoleRepository;
import com.JobPortal.ServiceImpl.RoleServiceImpl;
import com.JobPortal.entity.Roles;

@Service
public class RoleService implements RoleServiceImpl {

	@Autowired
	private RoleRepository repository;
	
	public RoleDto setRole(RoleDto dto)
	{
		Roles roles=new  Roles();
		
		roles.setRoles(dto.getRoles());
		this.repository.save(roles);
		
		return dto;
		
	}
}
