package com.arun.dto;

import org.jsondoc.core.annotation.ApiObjectField;

public class ShoeDTO {

	@ApiObjectField(description = "Shoe Id")
	private Long shoeId;

	@ApiObjectField(description = "Shoe Name")
	private String shoeName;

	@ApiObjectField(description = "Brand Name")
	private String brandName;

	@ApiObjectField(description = "Price")
	private Long price;
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
	 * @return the shoeName
	 */
	public String getShoeName() {
		return shoeName;
	}
	/**
	 * @param shoeName the shoeName to set
	 */
	public void setShoeName(String shoeName) {
		this.shoeName = shoeName;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	
	
}
