package com.JobPortal.Dto;

public class UserRoleDto {

	private long userId;
	
	private long roleId;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public UserRoleDto(long userId, long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public UserRoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
	
}
