package com.JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.UserRoleDto;
import com.JobPortal.Repository.RoleRepository;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Repository.UserRoleRepository;
import com.JobPortal.entity.Roles;
import com.JobPortal.entity.UserEntity;
import com.JobPortal.entity.UserRolesEntity;

@Service
public class UserRoleService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository repository;
	
	
	public UserRoleDto setUserRole(UserRoleDto dto) throws Exception
	{
		
		UserEntity entity=this.userRepository.findById(dto.getUserId()).orElseThrow(()-> new Exception("User not foud"));
		
		Roles roles=this.roleRepository.findById(dto.getRoleId()).orElseThrow(()-> new Exception("role not found"));
		
		
		UserRolesEntity  entity2=new UserRolesEntity();
		
		entity2.setUsers(entity);
		entity2.setRoles(roles);
		this.repository.save(entity2);
		
		return dto;
		
	}
	
	
	
	
}

