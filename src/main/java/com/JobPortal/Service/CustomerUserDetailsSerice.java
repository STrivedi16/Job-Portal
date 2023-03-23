package com.JobPortal.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.JobPortal.Config.RedisServer;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerUserDetailsSerice  implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private RedisServer redisServer;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity entity=new UserEntity();
		
		
		
		
//		UserEntity entity2=new UserEntity();
		
		
		if(!redisServer.isKeyExist(username, username))
		{
			entity=this.repository.findByEmailIgnoreCase(username);
			if(entity==null)
			{
				throw new ResourcesNotFoundException();
			}
			
			redisServer.addInCache(username, username, entity.toString());
		}
		else {
			String jsonString=(String) redisServer.getFromCache(username, username);
			try {
				ObjectMapper mapper=new ObjectMapper();
				Map<String , Object> map=mapper.readValue(jsonString, Map.class);
				System.out.println(map.toString());
				entity.setPassword((String)map.get("password"));
				entity.setEmail((String)map.get("email"));
				entity.setId((Integer)map.get("id"));
				
			}
			catch (Exception e) {
				System.out.println("ERROR"+ e);
			}
		}
		
		System.out.println("JSON STRING "+ entity.toString());
		
		ArrayList<SimpleGrantedAuthority>  permissions;
		if(entity!=null)
		{
			 permissions=this.service.getAuthorities(entity.getId());
			
			System.err.println(permissions);
		}
		else {
			throw new ResourcesNotFoundException();
		}
		
		
		return new User(entity.getEmail(), entity.getPassword(), permissions);
	}
	
	
	
	

}