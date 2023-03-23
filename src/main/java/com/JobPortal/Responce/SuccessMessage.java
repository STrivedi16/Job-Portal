package com.JobPortal.Responce;

public class SuccessMessage {

	
	private String successMessage;
	
	private String successKey;
	
	private Object object;

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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public SuccessMessage(String successMessage, String successKey, Object object) {
		super();
		this.successMessage = successMessage;
		this.successKey = successKey;
		this.object = object;
	}

	public SuccessMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
