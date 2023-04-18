package com.JobPortal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCsv {

	@Id
	private int id ;
	
	private String name;
	
	private String email;
	
	private String gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserCsv(int id, String name, String email, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
	}

	public UserCsv() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	




}

