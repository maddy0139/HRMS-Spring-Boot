package com.hrms.payload;

public class UserSummary {
	private Long id;
	private String email;
	private String username;
	public UserSummary() {
		super();
	}
	public UserSummary(Long id, String email, String username) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
