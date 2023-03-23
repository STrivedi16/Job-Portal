package com.JobPortal.Responce;

public class ErrorException {

	String message;
	
	StringBuffer key;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StringBuffer getKey() {
		return key;
	}

	public void setKey(StringBuffer key) {
		this.key = key;
	}

	public ErrorException(String message, StringBuffer key) {
		super();
		this.message = message;
		this.key = key;
	}

	public ErrorException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
