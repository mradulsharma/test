package com.madiv.freemarker;

public class Address {
	private String addressType;
	private String address;
	
	public Address(String addressType, String address) {
		this.addressType = addressType;
		this.address = address;
	}
	
	public String getAddressType() {
		return addressType;
	}
	
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}

