package com.arun.dto;

import org.jsondoc.core.annotation.ApiObjectField;

public class ShoeNameDTO {

	@ApiObjectField(description = "Shoe Name")
	private String shoeName;

	public String getShoeName() {
		return shoeName;
	}

	public void setShoeName(String shoeName) {
		this.shoeName = shoeName;
	}
}
