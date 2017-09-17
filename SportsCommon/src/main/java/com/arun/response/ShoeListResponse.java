package com.arun.response;

import java.util.List;

import com.arun.dto.ShoeDTO;

public class ShoeListResponse {

	private List<ShoeDTO> aaData;

	public List<ShoeDTO> getAaData() {
		return aaData;
	}

	public void setAaData(List<ShoeDTO> aaData) {
		this.aaData = aaData;
	}
}
