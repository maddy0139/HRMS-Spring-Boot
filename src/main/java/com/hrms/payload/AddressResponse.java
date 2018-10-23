package com.hrms.payload;



import com.hrms.model.User;
import com.hrms.model.UserAddress;

public class AddressResponse {

	private String address;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String phoneNumber;
	private UserSummary summary;
	
	public AddressResponse(UserAddress address)
	{
		super();
		this.address = address.getAddress1()+", "+address.getAddress2();
		this.city = address.getCity();
		this.state = address.getState();
		this.country = address.getCountry();
		this.zipCode = address.getZipCode();
		this.phoneNumber = address.getPhoneNumber();
		User user = address.getUser();
		this.summary = new UserSummary(user.getId(),user.getEmail(),user.getUserName());
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getState() {
		return state;
	}

	public UserSummary getSummary() {
		return summary;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setSummary(UserSummary summary) {
		this.summary = summary;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
