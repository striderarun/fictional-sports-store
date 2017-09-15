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
}
