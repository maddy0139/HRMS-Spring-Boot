package com.hrms.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "user_profiles")

public class UserProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@Size(max = 60)
	@Column(name = "last_name")
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@NotBlank
	@Size(max = 60)
	@Column(name = "is_active")
	private String isActive;
	
	@OneToOne (cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="designation_id", insertable=true, updatable=true)
	private Designation designation;
	

	@OneToOne (cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", unique= true, insertable=true, updatable=true)
	private User user;

	public UserProfile() {
		super();
	}

	
	public UserProfile(Long id, @NotBlank @Size(max = 60) String firstName, @NotBlank @Size(max = 60) String lastName,
			Gender gender, @NotBlank @Size(max = 60) String isActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.isActive = isActive;
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

	public Long getId() {
		return id;
	}

	public String getIsActive() {
		return isActive;
	}

	public String getLastName() {
		return lastName;
	}

	public User getUser() {
		return user;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", isActive=" + isActive + ", designation=" + designation + ", user=" + user + "]";
	}
	
	
}
