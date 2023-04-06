package com.JobPortal.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.UserDto;
import com.JobPortal.Interface.UserInterface;

@Service
public interface UserServiceImpl {

	public UserDto registerUser(UserDto dto) throws Exception;
	
	public List<UserInterface> getUserDetails(long id );
	
	public List<UserInterface> getUserDetailsProfile(long id );
	
	public List<UserInterface> getAll(Integer pagesize, Integer pagenumber);
	
	public ArrayList<SimpleGrantedAuthority> getAuthorities(long id );
	
	public String  forgotPassword(String email, UserDto dto) ;
	
}

