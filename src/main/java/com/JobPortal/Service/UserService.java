package com.JobPortal.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.UserDto;
import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserPermission;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public UserDto registerUser(UserDto dto) throws Exception
	{
		
		UserEntity userData=this.repository.findByEmailIgnoreCase(dto.getEmail());
		
		if(userData!=null)
			throw new Exception("user data alredy stroed");
		
		UserEntity entity=new UserEntity();
		entity.setName(dto.getName());
		entity.setCity(dto.getCity());
		entity.setState(dto.getState());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setMobileNo(dto.getMobileNo());
		
		this.repository.save(entity);
		
		return dto;
	}
	
	public List<UserInterface> getUserDetails(long id )
	{
		
		List<UserInterface>  list=this.repository.findById(id, UserInterface.class);
		if(list.isEmpty())
		{
			throw new ResourcesNotFoundException();
		}
		
		return list;
		
	}
	
public List<UserInterface> getAll(Integer pagesize, Integer pagenumber) {
		
		Pageable pageable=PageRequest.of(pagenumber, pagesize);
		
		Page<UserInterface> page=this.repository.findAll(pageable,UserInterface.class);
		
		
		
		return page.getContent();
	}
	
	
	
	public ArrayList<SimpleGrantedAuthority> getAuthorities(long id )
	{
		ArrayList<SimpleGrantedAuthority> arrayList=new ArrayList<>();
		
		if( id+"permission" !=null)
		{
			ArrayList<SimpleGrantedAuthority> arrayList2=new ArrayList<>();
			
			List<UserPermission> list=this.repository.getPermission(id, UserPermission.class);
			
			list.forEach(e ->
			
			arrayList2.add(new SimpleGrantedAuthority(e.getPermissions()))
					);
			
			arrayList=arrayList2;
		}
		
		return arrayList;
	}
	
	
	public String  forgotPassword(String email, UserDto dto) 
	{
		UserEntity entity=this.repository.findByEmailIgnoreCase(email);

		if(entity==null)
			{
			throw new ResourcesNotFoundException();
		}
		
		entity.setPassword(dto.getPassword());
		
		this.repository.save(entity);
	
		return "password updated ";
	}
	
	
	
}
