package com.hrms.payload;

import com.hrms.model.Designation;
import com.hrms.model.Gender;
import com.hrms.model.User;
import com.hrms.model.UserProfile;

public class UserProfileResponse {

	private String firstName;
	private String lastName;
	private Gender gender;
	private String isActive;
	private Designation designation;
	private UserSummary userSummary;
	
	public UserProfileResponse(UserProfile userProfile, Designation designation,User user) {
		super();
		this.firstName = userProfile.getFirstName();
		this.lastName = userProfile.getLastName();
		this.gender = userProfile.getGender();
		this.isActive = userProfile.getIsActive();
		this.designation = new Designation(designation.getId(), designation.getDesignation());
		this.userSummary = new UserSummary(user.getId(),user.getEmail(),user.getUserName());
	}
	
	public Designation getDesignation() {
		return designation;
	}
	public String getFirstName() {
		return firstName;
	}
	public Gender getGender() {
		return gender;
	}
	public String getIsActive() {
		return isActive;
	}
	public String getLastName() {
		return lastName;
	}
	
	public UserSummary getUserSummary() {
		return userSummary;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setUserSummary(UserSummary userSummary) {
		this.userSummary = userSummary;
	}
	
	
}
