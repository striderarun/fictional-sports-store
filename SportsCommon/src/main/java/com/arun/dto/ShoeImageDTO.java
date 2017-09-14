package com.arun.dto;

import org.jsondoc.core.annotation.ApiObjectField;

public class ShoeImageDTO {

	@ApiObjectField(description = "Shoe Id")
	private Long shoeId;

	@ApiObjectField(description = "Shoe Image")
	private byte[] shoeImage;

	public Long getShoeId() {
		return shoeId;
	}

	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
	}

	public byte[] getShoeImage() {
		return shoeImage;
	}

	public void setShoeImage(byte[] shoeImage) {
		this.shoeImage = shoeImage;
	}
}
