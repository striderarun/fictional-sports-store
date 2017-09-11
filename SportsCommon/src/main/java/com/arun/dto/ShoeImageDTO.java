package com.arun.dto;

import org.jsondoc.core.annotation.ApiObjectField;

public class ShoeImageDTO {

	@ApiObjectField(description = "Shoe Id")
	private Long shoeId;

	@ApiObjectField(description = "Shoe Image")
	private byte[] shoeImage;

	/**
	 * @return the shoeId
	 */
	public Long getShoeId() {
		return shoeId;
	}
	/**
	 * @param shoeId the shoeId to set
	 */
	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
	}
	/**
	 * @return the shoeImage
	 */
	public byte[] getShoeImage() {
		return shoeImage;
	}
	/**
	 * @param shoeImage the shoeImage to set
	 */
	public void setShoeImage(byte[] shoeImage) {
		this.shoeImage = shoeImage;
	}
	
	
}
