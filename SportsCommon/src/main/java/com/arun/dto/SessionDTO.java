package com.arun.dto;

import org.jsondoc.core.annotation.ApiObjectField;

public class SessionDTO {

	@ApiObjectField(description = "User Name")
	private String userName;

	@ApiObjectField(description = "Password")
	private String password;

	@ApiObjectField(description = "User Role")
	private String userRole;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
