package com.hrms.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.model.User;
import com.hrms.payload.ApiResponse;
import com.hrms.payload.ResponseBean;
import com.hrms.payload.UserSummary;
import com.hrms.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/users/all")
	private List<UserSummary> getUserDetails()
	{
		List<User> users = new ArrayList<>();
		users = userRepository.findAll();
		
		List<UserSummary> summaries = new ArrayList<>();
		users.forEach((user)->{
			summaries.add(new UserSummary(user.getId(),user.getEmail(),user.getUserName()));
		});
		return summaries;
	}
	
	@GetMapping("/users/{id}")
	private UserSummary getUserDetails(@PathVariable(value = "id")Long id)
	{
		User user = userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User", "id", id));
		
		UserSummary summary = new UserSummary(user.getId(),user.getEmail(),user.getUserName());
		return summary;
	}
	
	@PostMapping("/users/new")
	private ResponseEntity<ResponseBean> addUser(@RequestBody User user)
	{
		
		User result = userRepository.saveAndFlush(user);
		/*URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(result.getUserName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User created successfully"));*/
		
		ResponseBean responseBean = new ResponseBean(result);
		
		return new ResponseEntity<>(responseBean,HttpStatus.OK);
	}
	
//	@GetMapping("/users/active/{isActive}")
//	private List<User> getActiveUsers(@PathVariable(value = "isActive")String isActive)
//	{
//		return userRepository.findActiveUsers(isActive);
//	}
}
