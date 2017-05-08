package com.arun.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="SHOE")
@SequenceGenerator(name = "SHOE_SEQ", sequenceName = "SHOE_SEQ", allocationSize = 1)
@EntityListeners({AuditingEntityListener.class})
public class Shoes {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOE_SEQ")
	@Column(name="SHOE_ID")
	private Long shoeId;
	
	@Column(name="SHOE_NAME")
	private String shoeName;
	
	@Column(name="BRAND_NAME")
	private String brandName;
	
	@Column(name="PRICE")
	private Long price;
	
	@CreatedBy
	@Basic(optional = false)
	@Column(name="CREATED_BY", nullable = false, updatable = false)
	private String createdBy;
	
	@CreatedDate
	@Basic(optional = false)
	@Temporal(value = TemporalType.DATE)
	@Column(name="CREATED_DATE", nullable = false, updatable = false)
	private Date createdDate;
	
	@LastModifiedBy
	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	
	@LastModifiedDate
	@Temporal(value = TemporalType.DATE)
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	public Long getShoeId() {
		return shoeId;
	}

	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
	}

	public String getShoeName() {
		return shoeName;
	}

	public void setShoeName(String shoeName) {
		this.shoeName = shoeName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getCreatedDate() {
		return new Date(createdDate.getTime());
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = new Date(createdDate.getTime());
	}

	public Date getModifiedDate() {
		return new Date(modifiedDate.getTime());
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = new Date(modifiedDate.getTime());
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
	
}
