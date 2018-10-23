package com.hrms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "user_name"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank
	@Size(max = 60)
	private String email;
	
	@NotBlank
	@Size(max = 60)
	@Column(name = "user_name")
	private String userName;
	
	@NotBlank
	@Size(max = 128)
	private String password;
	
	@NotBlank
	@Size(max = 60)
	@Column(name = "is_active")
	private String isActive;

	public User() {
		super();
	}

	public User(Long id, @Email @NotBlank @Size(max = 60) String email, @NotBlank @Size(max = 60) String userName,
			@NotBlank @Size(max = 128) String password, @NotBlank @Size(max = 60) String isActive) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.isActive = isActive;
	}

	public String getEmail() {
		return email;
	}

	

	public Long getId() {
		return id;
	}

	public String getIsActive() {
		return isActive;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", userName=" + userName + ", password=" + password
				+ ", isActive=" + isActive + "]";
	}
	
	

}
