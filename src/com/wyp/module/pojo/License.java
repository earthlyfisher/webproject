package com.wyp.module.pojo;

public class License {
	
	private String requestUUID;
	
	private String name;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String postalcode;
	
	private String country;
	
	private String company;
	
	/**
	 * 版本:premium(永久版)，premium-trial(试用版)
	 */
	private String editon;
	
	/**
	 * 数量，不能超过100
	 */
	private String sockets;
	
	public String getRequestUUID() {
		return requestUUID;
	}

	public void setRequestUUID(String requestUUID) {
		this.requestUUID = requestUUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEditon() {
		return editon;
	}

	public void setEditon(String editon) {
		this.editon = editon;
	}

	public String getSockets() {
		return sockets;
	}

	public void setSockets(String sockets) {
		this.sockets = sockets;
	}

}
