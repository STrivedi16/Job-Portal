package com.JobPortal.entity;

import java.util.List;

import com.JobPortal.Interface.UserProfileInterface;

public class UserProfileJobsResponse {

	private String name;
	
	private String email;
	
	private String city;
	
	private String state;
	
	private Object expireance;
	
	private String jobs;
	
	private String role;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	


	

	public Object getExpireance() {
		return expireance;
	}

	public void setExpireance(Object expireance) {
		this.expireance = expireance;
	}

	public UserProfileJobsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getJobs() {
		return jobs;
	}

	public void setJobs(String jobs) {
		this.jobs = jobs;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserProfileJobsResponse(String name, String email, String city, String state,
			List<UserProfileInterface> expireance, String jobs, String role) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
		this.state = state;
		this.expireance = expireance;
		this.jobs = jobs;
		this.role = role;
	}


	
	
	
	
	
}
