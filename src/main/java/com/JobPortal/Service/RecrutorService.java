package com.JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.RecruterDto;
import com.JobPortal.Dto.RecruterStatusDto;
import com.JobPortal.Repository.RecruterRepo;
import com.JobPortal.ServiceImpl.RecruterServiceImpl;
import com.JobPortal.entity.RecruterEntity;
import com.netflix.discovery.converters.Auto;

@Service
public class RecrutorService implements RecruterServiceImpl {

	@Autowired
	private RecruterRepo recruterRepo;
	
	@Override
	public RecruterDto setDetails(RecruterDto dto)
	{
		RecruterEntity entity =new RecruterEntity();
		entity.setName(dto.getName());
		entity.setCompanyEmail(dto.getCompanyEmail());
		entity.setCompanyDiscription(dto.getCompanyDiscription());
		entity.setPassword(dto.getPassword());
		entity.setRegistrationNumber(dto.getRegistrationNumber());
		
		this.recruterRepo.save(entity);
		
		return dto;
	}

	@Override
	public RecruterEntity updateStatus(long id, RecruterStatusDto dto) throws Exception {
		
		RecruterEntity entity=this.recruterRepo.findById(id).orElseThrow(()-> new Exception("Recruter not found"));
		
		entity.setStatus(dto.getStatus());
		
		return  this.recruterRepo.save(entity);
		
		
	}
	
	
	
	
}
