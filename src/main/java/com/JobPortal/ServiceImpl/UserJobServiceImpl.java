package com.JobPortal.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.JobPortal.Dto.UserJobDto;
import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserJobInterface;
import com.JobPortal.entity.UserJobsEntity;
import com.JobPortal.entity.UserProfileJobsResponse;

@Service
public interface UserJobServiceImpl {

	
	public UserJobDto setUserJob(UserJobDto dto) throws Exception;
	
	public List<UserJobInterface> getUserJob(long id, Integer pagenumber, Integer pagesize);

	public List<UserInterface> getAllCandidate(String name, Integer pagenumber, Integer pagesize );
	
	public UserJobsEntity updateStatus(long id, UserJobDto  dto) throws Exception;
	
	public ArrayList<UserProfileJobsResponse> getCandidates(long id);




}
