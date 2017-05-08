package com.arun.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USER_EMAIL")
@SequenceGenerator(name = "USER_EMAIL_SEQ", sequenceName = "USER_EMAIL_SEQ", allocationSize = 1)
public class UserEmail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_EMAIL_SEQ")
	@Column(name="EMAILID")
	private Long emailId;
	
	@Column(name="EMAIL")
	private String email;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USERID")
	private UserDetails userDetails;
	
	/**
	 * @return the emailId
	 */
	
	public Long getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the userDetails
	 */
	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	
	
	
}
