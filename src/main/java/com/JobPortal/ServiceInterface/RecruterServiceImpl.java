package com.JobPortal.ServiceInterface;

import java.util.List;

import org.springframework.stereotype.Service;

import com.JobPortal.Dto.RecruterDto;
import com.JobPortal.Dto.RecruterStatusDto;
import com.JobPortal.entity.RecruterEntity;

import io.swagger.v3.oas.annotations.servers.Server;

@Service
public interface RecruterServiceImpl {

	
	 RecruterDto setDetails(RecruterDto dto);
	 
	 

	RecruterEntity updateStatus(long id, RecruterStatusDto dto) throws Exception;
	
	public List<RecruterDto> getDetails() throws Exception;
}
