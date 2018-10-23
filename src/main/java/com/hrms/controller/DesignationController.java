package com.hrms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hrms.exception.ResourceNotFoundException;
import com.hrms.model.Designation;
import com.hrms.payload.ApiResponse;
import com.hrms.payload.ResponseBean;
import com.hrms.repository.DesignationRepository;

@RestController
@RequestMapping("/api")
public class DesignationController {

	@Autowired
	private DesignationRepository designationRepository;
	
	@GetMapping("/designation/all")
	private ResponseEntity<ResponseBean> getAlldesignation()
	{
		List<Designation> designations = designationRepository.findAll();
		ResponseBean responseBean = new ResponseBean(designations);
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}
	
	@GetMapping("/designation/{id}")
	private ResponseEntity<ResponseBean> getdesignation(@PathVariable(value = "id")Long id)
	{
		Designation designation = designationRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Designation","id",id));
		ResponseBean responseBean = new ResponseBean(designation);
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}
	
	@PostMapping("/designation/new")
	private ResponseEntity<ApiResponse> addNewrepository(@RequestBody Designation designation)
	{
		designationRepository.saveAndFlush(designation);
        ApiResponse response = new ApiResponse(true, "Designation added successfully");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	
}
