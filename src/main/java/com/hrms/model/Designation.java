package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "designations",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "designation"
            })})
public class Designation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String designation;

	public Designation() {
		super();
	}

	public Designation(Long id, @NotBlank @Size(max = 100) String designation) {
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

	@Override
	public String toString() {
		return "Designation [id=" + id + ", designation=" + designation + "]";
	}
	
	
	
	
}
