package com.JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.RecruterDto;
import com.JobPortal.Dto.RecruterStatusDto;
import com.JobPortal.Repository.RecruterRepo;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.ServiceInterface.RecruterServiceImpl;
import com.JobPortal.entity.CompanyStatus;
import com.JobPortal.entity.RecruterEntity;
import com.JobPortal.entity.UserEntity;
import com.netflix.discovery.converters.Auto;

@Service
public class RecrutorService implements RecruterServiceImpl {

	@Autowired
	private RecruterRepo recruterRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public RecruterDto setDetails(RecruterDto dto)
	{
		RecruterEntity entity =new RecruterEntity();
		UserEntity entity2=new UserEntity();
		
		entity.setName(dto.getCompanyName());
		entity.setCompanyEmail(dto.getEmail());
		entity.setCompanyDiscription(dto.getDiscription());
		
		String password=this.encoder.encode(dto.getPassword());
		entity.setPassword(password);
		entity.setRegistrationNumber(dto.getRegistrationNumber());
		entity.setStatus(CompanyStatus.PROCESSING);
		
		
//		entity2.setEmail(dto.getCompanyName());
//		entity2.setPassword(dto.getPassword());
//		entity2.setCity(dto.getCity());
//		entity2.setState(dto.getState());
//		entity2.setName(dto.getCompanyName());
//		entity2.setMobileNo(dto.getMobileNo());
//		
//		this.repository.save(entity2);
		
		this.recruterRepo.save(entity);
		
		return dto;
	}
	
	
	

	@Override
	public RecruterEntity updateStatus(long id, RecruterStatusDto dto)  {
		
		RecruterEntity entity=this.recruterRepo.findById(id).orElseThrow(()-> new ResourcesNotFoundException());
		
		entity.setStatus(dto.getStatus());
		
		if(entity.getStatus().equals(CompanyStatus.REGISTER))
		{
			UserEntity entity2=new  UserEntity();
			
			entity2.setName(entity.getName());
			entity2.setEmail(entity.getCompanyEmail());
			entity2.setPassword(entity.getPassword());
			
			this.userRepository.save(entity2);
		}
		else if(entity.getStatus().equals(CompanyStatus.REGECTED)){
			
			entity.setCompanyEmail(null);
			entity.setCompanyDiscription(null);
			entity.setName(null);
			entity.setCreationTime(null);
			entity.setRegistrationNumber(null);
			
			this.recruterRepo.save(entity);
		}
		
		
		return  this.recruterRepo.save(entity);
		
		
	}




	@Override
	public RecruterDto getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
