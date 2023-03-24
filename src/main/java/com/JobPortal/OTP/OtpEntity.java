package com.JobPortal.OTP;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class OtpEntity {

	@Id
	private long id;
	
	@Column(length = 4)
	private int otp;
	
	private String email;
	
	private Timestamp otpValidation;
	
	@CreationTimestamp
	private Timestamp creationTime;
	
	@UpdateTimestamp
	private Timestamp updationTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getOtpValidation() {
		return otpValidation;
	}

	public void setOtpValidation(Timestamp otpValidation) {
		this.otpValidation = otpValidation;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getUpdationTime() {
		return updationTime;
	}

	public void setUpdationTime(Timestamp updationTime) {
		this.updationTime = updationTime;
	}

	public OtpEntity(long id, int otp, String email, Timestamp otpValidation, Timestamp creationTime,
			Timestamp updationTime) {
		super();
		this.id = id;
		this.otp = otp;
		this.email = email;
		this.otpValidation = otpValidation;
		this.creationTime = creationTime;
		this.updationTime = updationTime;
	}

	public OtpEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
