package com.hrms.payload;

public class DesignationResponse {

	private Long id;
	private String designation;
	public DesignationResponse(Long id, String designation) {
		super();
		this.id = id;
		this.designation = designation;
	}
	public String getDesignation() {
		return designation;
	}
	public Long getId() {
		return id;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
