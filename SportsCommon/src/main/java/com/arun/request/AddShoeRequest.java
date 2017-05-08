package com.arun.request;

public class AddShoeRequest {

	private String shoeName;
	private String brandName;
	private Long price;
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
