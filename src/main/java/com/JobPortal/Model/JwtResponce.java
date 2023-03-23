package com.JobPortal.Model;

public class JwtResponce {

	String token;
	
	String refToken;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefToken() {
		return refToken;
	}

	public void setRefToken(String refToken) {
		this.refToken = refToken;
	}

	public JwtResponce(String token, String refToken) {
		super();
		this.token = token;
		this.refToken = refToken;
	}

	public JwtResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
