package com.JobPortal.ServiceInterface;

import java.util.List;

import com.JobPortal.Dto.JobDto;
import com.JobPortal.Interface.JobInterface;

public interface JobServiceInterface {

	
	public JobDto setJob(JobDto dto);
	
	public List<JobInterface> searchJob(int pagesize, int pagenumber, String role, String state);
	
	
}
