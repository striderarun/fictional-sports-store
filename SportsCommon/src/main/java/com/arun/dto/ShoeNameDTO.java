package com.arun.dto;

import org.jsondoc.core.annotation.ApiObjectField;

public class ShoeNameDTO {

	@ApiObjectField(description = "Shoe Name")
	private String shoeName;

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
	
	
	
}
