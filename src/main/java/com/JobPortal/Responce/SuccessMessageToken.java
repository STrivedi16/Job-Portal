package com.JobPortal.Responce;

public class SuccessMessageToken {

	private String successMessage;
	
	private String successKey;
	
	private String token;
	
	private String refToken;

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getSuccessKey() {
		return successKey;
	}

	public void setSuccessKey(String successKey) {
		this.successKey = successKey;
	}

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

	public SuccessMessageToken(String successMessage, String successKey, String token, String refToken) {
		super();
		this.successMessage = successMessage;
		this.successKey = successKey;
		this.token = token;
		this.refToken = refToken;
	}

	public SuccessMessageToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
