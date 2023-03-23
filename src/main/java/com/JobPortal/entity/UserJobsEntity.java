package com.JobPortal.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update user_jobs_entity set is_active=false where id=?")
@Where(clause = "is_active=true")
public class UserJobsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private JobsEntity  jobEntity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public JobsEntity getJobEntity() {
		return jobEntity;
	}

	public void setJobEntity(JobsEntity jobEntity) {
		this.jobEntity = jobEntity;
	}

	public UserJobsEntity(long id, UserEntity userEntity, JobsEntity jobEntity) {
		super();
		this.id = id;
		this.userEntity = userEntity;
		this.jobEntity = jobEntity;
	}

	public UserJobsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
