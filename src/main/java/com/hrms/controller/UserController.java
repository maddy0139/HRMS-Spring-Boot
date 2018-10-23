package com.hrms.controller;


import java.util.ArrayList;
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
import com.hrms.model.User;

import com.hrms.payload.ResponseBean;
import com.hrms.payload.UserSummary;
import com.hrms.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/users/all")
	private ResponseEntity<ResponseBean> getUserDetails()
	{
		List<User> users = new ArrayList<>();
		users = userRepository.findAll();
		
		List<UserSummary> summaries = new ArrayList<>();
		users.forEach((user)->{
			summaries.add(new UserSummary(user.getId(),user.getEmail(),user.getUserName()));
		});
		ResponseBean response = new ResponseBean(summaries);
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	private ResponseEntity<ResponseBean> getUserDetails(@PathVariable(value = "id")Long id)
	{
		User user = userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User", "id", id));
		
		UserSummary summary = new UserSummary(user.getId(),user.getEmail(),user.getUserName());
		ResponseBean response = new ResponseBean(summary);
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	
	@PostMapping("/users/new")
	private ResponseEntity<ResponseBean> addUser(@RequestBody User user)
	{
		
		User result = userRepository.saveAndFlush(user);
		ResponseBean responseBean = new ResponseBean(result);
		
		return new ResponseEntity<>(responseBean,HttpStatus.OK);
	}
	
}
