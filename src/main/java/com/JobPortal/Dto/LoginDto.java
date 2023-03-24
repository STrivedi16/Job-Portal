package com.JobPortal.Dto;

public class LoginDto {

	private String username;
	
	private int otp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public LoginDto(String username, int otp) {
		super();
		this.username = username;
		this.otp = otp;
	}

	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
