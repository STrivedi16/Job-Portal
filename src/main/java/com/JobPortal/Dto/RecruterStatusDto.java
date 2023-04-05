package com.JobPortal.Dto;

import com.JobPortal.entity.CompanyStatus;

public class RecruterStatusDto {

	long id ;
	
	CompanyStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CompanyStatus getStatus() {
		return status;
	}

	public void setStatus(CompanyStatus status) {
		this.status = status;
	}
	
	
	
}
