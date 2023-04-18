package com.JobPortal.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity

public class FileEntity {

	@Id
	private int id ;
	
	private String fileName;
	
	private String filetype;
	
	private long size;
	
	private Timestamp creationTimestamp;
	
	private Timestamp updationTimestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Timestamp getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Timestamp creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Timestamp getUpdationTimestamp() {
		return updationTimestamp;
	}

	public void setUpdationTimestamp(Timestamp updationTimestamp) {
		this.updationTimestamp = updationTimestamp;
	}

	public FileEntity(int id, String fileName, String filetype, long size, Timestamp creationTimestamp,
			Timestamp updationTimestamp) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filetype = filetype;
		this.size = size;
		this.creationTimestamp = creationTimestamp;
		this.updationTimestamp = updationTimestamp;
	}

	public FileEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
