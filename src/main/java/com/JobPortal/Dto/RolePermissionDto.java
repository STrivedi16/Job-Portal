package com.JobPortal.Dto;

public class RolePermissionDto {

	private long roleId;
	
	private long permissionId;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public RolePermissionDto(long roleId, long permissionId) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public RolePermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
