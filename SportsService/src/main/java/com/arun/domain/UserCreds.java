package com.arun.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USER_CREDS")
@SequenceGenerator(name = "USER_CREDS_SEQ", sequenceName = "USER_CREDS_SEQ", allocationSize = 1)
public class UserCreds {

	@Id
	@Column(name="USERID")
	private String userId;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToOne(mappedBy = "userCreds")
	private UserDetails userDetails;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	
	




}
