package com.JobPortal.Dto;

public class RecruterDto {

	private String companyName;
	
	private String email;
	
	private String discription;
	
	private String registrationNumber;
	
	private String password;
	
	
	private String city;
	private String state;
	
	private String mobileNo;

	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public RecruterDto(String companyName, String email, String discription, String registrationNumber, String password,
			String city, String state, String mobileNo) {
		super();
		this.companyName = companyName;
		this.email = email;
		this.discription = discription;
		this.registrationNumber = registrationNumber;
		this.password = password;
		this.city = city;
		this.state = state;
		this.mobileNo = mobileNo;
	}

	public RecruterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
