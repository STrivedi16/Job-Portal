package com.JobPortal.Dto;

public class JobDto {

	private String job;
	
	private String role;
	
	private String discription;
	
	private String salary;
	
	private String company;
	
	private String requirement;
	
	private String helplineDetails;
	
	private long validDateTimeInDays;
	
	private int opening;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRole() {
		return role;
	}

	
	

	



	public int getOpening() {
		return opening;
	}

	public void setOpening(int opening) {
		this.opening = opening;
	}

	public JobDto(String job, String role, String discription, String salary, String company, String requirement,
			String helplineDetails, long validDateTimeInDays) {
		super();
		this.job = job;
		this.role = role;
		this.discription = discription;
		this.salary = salary;
		this.company = company;
		this.requirement = requirement;
		this.helplineDetails = helplineDetails;
		this.validDateTimeInDays = validDateTimeInDays;
	}

	public long getValidDateTimeInDays() {
		return validDateTimeInDays;
	}

	public void setValidDateTimeInDays(long validDateTimeInDays) {
		this.validDateTimeInDays = validDateTimeInDays;
	}

	public String getHelplineDetails() {
		return helplineDetails;
	}

	public void setHelplineDetails(String helplineDetails) {
		this.helplineDetails = helplineDetails;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public JobDto(String job, String role, String discription, String salary, String company, String requirement) {
		super();
		this.job = job;
		this.role = role;
		this.discription = discription;
		this.salary = salary;
		this.company = company;
		this.requirement = requirement;
	}

	public JobDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobDto(String job, String role, String discription, String salary, String company, String requirement,
			String helplineDetails, long validDateTimeInDays, int opening) {
		super();
		this.job = job;
		this.role = role;
		this.discription = discription;
		this.salary = salary;
		this.company = company;
		this.requirement = requirement;
		this.helplineDetails = helplineDetails;
		this.validDateTimeInDays = validDateTimeInDays;
		this.opening = opening;
	}
	
	
	
	
}
