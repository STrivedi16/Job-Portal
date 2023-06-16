package com.JobPortal.Responce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SuccessMessageToken {

	private String successMessage;
	
	private String successKey;
	
	private String token;
	
	private String refToken;
	
	private ArrayList<String> permission;
	
	
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

	

	public SuccessMessageToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String> getPermission() {
		return permission;
	}

	public void setPermission(ArrayList<String> permission) {
		this.permission = permission;
	}

	public SuccessMessageToken(String successMessage, String successKey, String token, String refToken,
			ArrayList<String> permission) {
		super();
		this.successMessage = successMessage;
		this.successKey = successKey;
		this.token = token;
		this.refToken = refToken;
		this.permission = permission;
	}

	

	
	
	
	
	
}
