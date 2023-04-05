package com.JobPortal.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.JobPortal.Dto.UserJobDto;
import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserJobInterface;
import com.JobPortal.Interface.UserProfileInterface;
import com.JobPortal.Interface.UsersJobsInterface;
import com.JobPortal.Repository.JobRepository;
import com.JobPortal.Repository.UserJobRepository;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.entity.JobsEntity;
import com.JobPortal.entity.UserEntity;
import com.JobPortal.entity.UserJobsEntity;
import com.JobPortal.entity.UserProfileJobsResponse;
import com.netflix.discovery.converters.Auto;

import io.swagger.v3.oas.models.examples.Example;

@Service
public class UserJobService {

	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserJobRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	

	
	public UserJobDto setUserJob(UserJobDto dto) throws Exception
	{
		UserEntity entity=this.userRepository.findById(dto.getUserId()).orElseThrow(()-> new Exception("user not found"));
		
		JobsEntity jobsEntity=this.jobRepository.findById(dto.getJobId()).orElseThrow(()-> new Exception("job not found "));
		
		UserJobsEntity entity2=new UserJobsEntity();
		entity2.setUserEntity(entity);
		entity2.setJobEntity(jobsEntity);
		entity2.setStatus(Status.NONE);
		
		this.repository.save(entity2);
	
		return dto;
	}
	
	public List<UserJobInterface> getUserJob(long id, Integer pagenumber, Integer pagesize)
	{
		Pageable pageable=PageRequest.of(pagenumber, pagesize);
		
			Page<UserJobInterface> page=this.repository.findById(id,pageable, UserJobInterface.class);
	
			List<UserJobInterface> list=page.getContent();
			
			if(list.isEmpty())

			{
				throw new ResourcesNotFoundException();
			}
			return list;
			
	}
	
	
	public List<UserInterface> getAllCandidate(String name, Integer pagenumber, Integer pagesize )
	{
		Pageable pageable=PageRequest.of(pagenumber, pagesize);
		
		Page<UserInterface> page=this.repository.getAllCandidats(name, UserInterface.class,pageable);
		
		List<UserInterface> list=page.getContent();
		
		return list;
		
	}

	
	public UserJobsEntity updateStatus(long id, UserJobDto  dto) throws Exception
	{
		UserJobsEntity entity=this.repository.findById(id).orElseThrow(()-> new Exception("User job not found"));
		
		System.out.println(entity);
	
		entity.setStatus(dto.getStatus());
		
		return this.repository.save(entity);
		
	}
	
//	public ArrayList<UserProfileJobsResponse> getCandidates(long id)
//	{
//		List<UsersJobsInterface>list=this.repository.getApplyCandidates(id, UsersJobsInterface.class);
//		
//		UserProfileJobsResponse response=new UserProfileJobsResponse();
//		
//
//		
//		long userId = 0;
//		String name = null;
//		String email= null;
//		String city= null;
//		String state= null;
//		String expireance= null;
//		String role= null;
//		String job= null;
//		
//		ArrayList<UserProfileJobsResponse>al=new ArrayList<>();
//		
//		for(int i=0;i<list.size();i++)
//		{
//		
////		for(UsersJobsInterface jobsInterface:list)
////		{
////			userId=jobsInterface.getId();
////			name=jobsInterface.getName();
////			email=jobsInterface.getEmail();
////			city=jobsInterface.getCity();
////			state=jobsInterface.getState();
////			role=jobsInterface.getRole();
////			job=jobsInterface.getJobs();
////			
////			
////			
////		}
//			
//			UsersJobsInterface jobsInterface=list.get(i);
//			userId=jobsInterface.getId();
//			name=jobsInterface.getName();
//			email=jobsInterface.getEmail();
//			city=jobsInterface.getCity();
//			state=jobsInterface.getState();
//			role=jobsInterface.getRole();
//			job=jobsInterface.getJobs();
//			
//			
//			String url="http://Profile-Service/api/user/profile/"+userId;
//			
//			List<UserProfileInterface>  s=this.restTemplate.getForObject(url, List.class);
//			
//			
//			
//			
//			
//			
//			response.setExpireance(s);
//			
//		response.setName(name);
//		response.setEmail(email);
//		response.setCity(city);
//		response.setState(state);
//		response.setJobs(job);
//		response.setRole(role);
//		
//		al.add(response);
//		
//		userId++;
//		
//		
//		}
//		
//	
//		
//		
//		
//		
////		List<Object> list3=new ArrayList<>();
////		
////		list3.add(response);
////		
////		list3.add(list2);
////		list3.add(list);
//		
////		
////		if(list3.isEmpty())
////		{
////			throw new ResourcesNotFoundException();
////		}
//		
//		return   al;
//	
//	}
	
	
	public ArrayList<UserProfileJobsResponse> getCandidates(long id) {
	    List<UsersJobsInterface> list = this.repository.getApplyCandidates(id, UsersJobsInterface.class);
	    ArrayList<UserProfileJobsResponse> al = new ArrayList<>();
	    
	    for (int i = 0; i < list.size(); i++) {
	        UserProfileJobsResponse response = new UserProfileJobsResponse();
	        UsersJobsInterface jobsInterface = list.get(i);
	        long userId = jobsInterface.getId();
	        System.err.println(userId);
	        String name = jobsInterface.getName();
	        String email = jobsInterface.getEmail();
	        String city = jobsInterface.getCity();
	        String state = jobsInterface.getState();
	        String role = jobsInterface.getRole();
	        String job = jobsInterface.getJobs();
	        
	        try {
	        
	        String url = "http://Profile-Service/api/user/profile/" + userId;
	        List<UserProfileInterface> s = this.restTemplate.getForObject(url, List.class);
	        
	        String expireance = null;
//	        
	        response.setName(name);
	        response.setEmail(email);
	        response.setCity(city);
	        response.setState(state);
	        response.setJobs(job);
	        response.setRole(role);
	        response.setExpireance(s);
	        al.add(response);
	        
       }
	        catch (Exception e) {
	        	
//	        	e.printStackTrace();
	        	response.setName(name);
		        response.setEmail(email);
		        response.setCity(city);
		        response.setState(state);
		        response.setJobs(job);
		        response.setRole(role);
		        response.setExpireance(null);
		        al.add(response);
			}
	        
//	        for (UserProfileInterface profile : s) {
//	            expireance = profile.getExpireance();
//	        }
	        
	        
	        
	   }
	    
	    if (al.isEmpty()) {
	        throw new ResourcesNotFoundException();
	    }
	    
	    return al;
	}

	
}
