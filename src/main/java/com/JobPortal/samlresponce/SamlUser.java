package com.JobPortal.samlresponce;

public class SamlUser {
	
	String emaill;
	
	String status;

	public String getEmaill() {
		return emaill;
	}

	public void setEmaill(String emaill) {
		this.emaill = emaill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SamlUser(String emaill, String status) {
		super();
		this.emaill = emaill;
		this.status = status;
	}

	public SamlUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
