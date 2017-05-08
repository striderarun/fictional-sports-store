package com.arun.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USER_DETAILS")
@SequenceGenerator(name = "USER_DETAILS_SEQ", sequenceName = "USER_DETAILS_SEQ", allocationSize = 1)
public class UserDetails {
	
	@Id
	@Column(name="USERID")
	private String userId;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="USERROLE")
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "ADDRESSID")
	private UserAddress userAddress;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userDetails")
	private Set<UserEmail> userEmails;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userDetails")
	private Set<UserPhone> userPhones;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
	private UserCreds userCreds;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Set<UserEmail> getUserEmails() {
		return userEmails;
	}

	public void setUserEmails(Set<UserEmail> userEmails) {
		this.userEmails = userEmails;
	}

	public Set<UserPhone> getUserPhones() {
		return userPhones;
	}

	public void setUserPhones(Set<UserPhone> userPhones) {
		this.userPhones = userPhones;
	}

	public UserCreds getUserCreds() {
		return userCreds;
	}

	public void setUserCreds(UserCreds userCreds) {
		this.userCreds = userCreds;
	}
	

}
