package com.JobPortal.Dto;

public class UserJobDto {

	private long userId;
	
	private long jobId;

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
