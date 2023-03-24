package com.JobPortal.Service;

import java.util.List;

import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.UserJobDto;
import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserJobInterface;
import com.JobPortal.Interface.UsersJobsInterface;
import com.JobPortal.Repository.JobRepository;
import com.JobPortal.Repository.UserJobRepository;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.entity.JobsEntity;
import com.JobPortal.entity.UserEntity;
import com.JobPortal.entity.UserJobsEntity;

import io.swagger.v3.oas.models.examples.Example;

@Service
public class UserJobService {

	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserJobRepository repository;
	

	
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
	
	public List<UsersJobsInterface> getCandidates(long id)
	{
		List<UsersJobsInterface>list=this.repository.getApplyCandidates(id, UsersJobsInterface.class);
		
		if(list.isEmpty())
		{
			throw new ResourcesNotFoundException();
		}
		
		return list;
	
	}
	
}
