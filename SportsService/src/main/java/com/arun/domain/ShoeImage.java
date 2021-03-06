package com.arun.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SHOE_IMAGE")
@SequenceGenerator(name = "SHOE_IMAGE_SEQ", sequenceName = "SHOE_IMAGE_SEQ", allocationSize = 1)
public class ShoeImage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOE_IMAGE_SEQ")
	@Column(name="SHOE_ID")
	private Long shoeId;

	@Lob
	@Column(name="SHOE_IMAGE")
	private byte[] shoeImg;

	public Long getShoeId() {
		return shoeId;
	}

	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
	}

	public byte[] getShoeImg() {
		return shoeImg;
	}

	public void setShoeImg(byte[] shoeImg) {
		this.shoeImg = shoeImg;
	}
}
