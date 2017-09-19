package com.arun.response;

import java.util.List;

import com.arun.dto.ShoeNameDTO;

public class ShoeNameResponse {

	private List<ShoeNameDTO> shoeNames;

	public List<ShoeNameDTO> getShoeNames() {
		return shoeNames;
	}

	public void setShoeNames(List<ShoeNameDTO> shoeNames) {
		this.shoeNames = shoeNames;
	}
}
