package com.hrms.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.model.Designation;
import com.hrms.payload.ApiResponse;
import com.hrms.repository.DesignationRepository;

@RestController
@RequestMapping("/api")
public class DesignationController {

	@Autowired
	private DesignationRepository designationRepository;
	
	@GetMapping("/designation/all")
	private List<Designation> getAlldesignation()
	{
		return designationRepository.findAll();
	}
	
	@GetMapping("/designation/{id}")
	private Designation getdesignation(@PathVariable(value = "id")Long id)
	{
		Designation designation = designationRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Designation","id",id));
		return designation;
	}
	
	@PostMapping("/designation/new")
	private ResponseEntity<?> addNewrepository(@RequestBody Designation designation)
	{
		Designation result = designationRepository.saveAndFlush(designation);
		URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/designation/{id}")
                .buildAndExpand(result.getDesignation()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Designation added successfully"));
	}
	
	
}
