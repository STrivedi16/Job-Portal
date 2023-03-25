package com.JobPortal.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.JobPortal.Dto.UserDto;
import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserPermission;
import com.JobPortal.Interface.UserProfileInterface;
import com.JobPortal.Interface.UserProfileMerge;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
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
		
//		Collection<? extends UserInterface> li=new ArrayList<>();
//		li.add()
		
//		String url="http://Profile-Service/user/profile/"+id;
//		List=this.restTemplate.getForObject(url, UserProfileInterface.class);
		
		List<UserInterface>  list=this.repository.findById(id, UserInterface.class);
		//list.addAll(li);
		
		if(list.isEmpty())
		{
			throw new ResourcesNotFoundException();
		}
		
		return list;
		
	}
	
	public List<Object> getUserDetailsProfile(long id )
	{
		
//		Collection<? extends UserInterface> li=new ArrayList<>();
//		li.add()
		
//		String url="http://Profile-Service/user/profile/"+id;
//		List=this.restTemplate.getForObject(url, UserProfileInterface.class);
		
		String url="http://Profile-Service/api/user/profile/"+id;
		List<UserProfileInterface>  s=this.restTemplate.getForObject(url, List.class);
		
		
		
		List<UserInterface>  list=this.repository.findById(id, UserInterface.class);
		
		List<Object> list2=new ArrayList<>();
		
		list2.addAll(list);
		list2.add(s);
		
		
//		if(list.isEmpty())
//		{
//			throw new ResourcesNotFoundException();
//		}
		
		return list2;
		
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
