package com.JobPortal.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.JobDto;
import com.JobPortal.Interface.JobInterface;
import com.JobPortal.Repository.JobRepository;
import com.JobPortal.entity.JobsEntity;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;
	
	
	
	public JobDto setJob(JobDto dto)
	{
		JobsEntity entity=new JobsEntity();
		entity.setJobs(dto.getJob());
		entity.setDiscription(dto.getDiscription());
		entity.setCompany(dto.getCompany());
		entity.setRequirement(dto.getRequirement());
		entity.setRole(dto.getRole());
		entity.setSalary(dto.getSalary());
		entity.setHelplineDetails(dto.getHelplineDetails());
		
		long validateTime = 1440 * dto.getValidDateTimeInDays();
		
		Date date=new Date();
		Timestamp ts=new Timestamp(date.getTime()+validateTime);
		
		this.jobRepository.save(entity);
		
		return dto;
	}
	
	public List<JobInterface> searchJob(int pagesize, int pagenumber)
	{
		org.springframework.data.domain.Pageable pageable=PageRequest.of(pagenumber, pagesize);
		
		Page<JobInterface> page=this.jobRepository.findAll(pageable,JobInterface.class);
		
		
		
		List<JobInterface> list=page.getContent();
	
		
		return list;
	}

}
