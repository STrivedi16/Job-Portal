package com.JobPortal.Dto;

import com.JobPortal.Service.Status;

public class UserJobDto {

	private long userId;
	
	private long jobId;
	
	private Status status;
	
	

	public UserJobDto(long userId, long jobId, Status status) {
		super();
		this.userId = userId;
		this.jobId = jobId;
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public UserJobDto(long userId, long jobId) {
		super();
		this.userId = userId;
		this.jobId = jobId;
	}

	public UserJobDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
