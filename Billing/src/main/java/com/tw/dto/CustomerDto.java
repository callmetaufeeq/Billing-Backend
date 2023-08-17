package com.tw.dto;

public class CustomerDto {
	
	private Long id;
	private String customerName;
	private String address;
	private String mobileNumber;
	
	public CustomerDto() {
		super();
	}
	public CustomerDto(Long id, String customerName, String address, String mobileNumber) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.address = address;
		this.mobileNumber = mobileNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
