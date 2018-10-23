package com.hrms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_address")
public class UserAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(max = 100)
	private String address1;
	
	@Size(max = 100)
	private String address2;
	
	@Size(max = 60)
	private String city;
	
	@Size(max = 60)
	private String state;
	
	@Size(max = 60)
	private String country;
	
	@Size(max = 60)
	@Column(name = "zip_code")
	private String zipCode;
	
	@Size(max = 15)
	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public UserAddress() {
		super();
	}

	public UserAddress(long id, @Size(max = 100) String address1, @Size(max = 100) String address2,
			@Size(max = 60) String city, @Size(max = 60) String state, @Size(max = 60) String country,
			@Size(max = 60) String zipCode, @Size(max = 15) String phoneNumber) {
		super();
		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public long getId() {
		return id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getState() {
		return state;
	}

	public User getUser() {
		return user;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
	
}
