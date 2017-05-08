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
@Table(name="USER_PHONE")
@SequenceGenerator(name = "USER_PHONE_SEQ", sequenceName = "USER_PHONE_SEQ", allocationSize = 1)
public class UserPhone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PHONE_SEQ")
	@Column(name="PHONEID")
	private Long phoneId;
	
	@Column(name="PHONENO")
	private String phoneNo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USERID")
	private UserDetails userDetails;
	
	/**
	 * @return the phoneId
	 */
	
	public Long getPhoneId() {
		return phoneId;
	}
	/**
	 * @param phoneId the phoneId to set
	 */
	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
