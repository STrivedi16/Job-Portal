package com.JobPortal.Dto;

public class RecruterDto {

	String name;
	
	String companyEmail;
	
	String companyDiscription;
	
	String registrationNumber;
	
	String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyDiscription() {
		return companyDiscription;
	}

	public void setCompanyDiscription(String companyDiscription) {
		this.companyDiscription = companyDiscription;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RecruterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecruterDto(String name, String companyEmail, String companyDiscription, String registrationNumber,
			String password) {
		super();
		this.name = name;
		this.companyEmail = companyEmail;
		this.companyDiscription = companyDiscription;
		this.registrationNumber = registrationNumber;
		this.password = password;
	}
	
	
	
}
