package com.JobPortal.Responce;

public class SuccessMsg {

	private String message;
	
	private String key;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SuccessMsg(String message, String key) {
		super();
		this.message = message;
		this.key = key;
	}

	public SuccessMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
