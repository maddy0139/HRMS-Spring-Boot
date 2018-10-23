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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.exception.ResourceNotFoundException;
import com.hrms.model.Designation;
import com.hrms.model.User;
import com.hrms.model.UserProfile;
import com.hrms.payload.ResponseBean;
import com.hrms.payload.UserProfileResponse;
import com.hrms.repository.DesignationRepository;
import com.hrms.repository.UserProfileRepository;
import com.hrms.repository.UserRepository;
import com.hrms.util.AppConstants;

@RestController
@RequestMapping("/api")
public class UserProfileController {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DesignationRepository designationRepository;
	
	
	@GetMapping("/userProfiles/{id}")
	private UserProfile getUserProfile(@PathVariable(value = "id")Long id)
	{
		UserProfile profile = userProfileRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("UserProfile", "id", id));
		
		return profile;
	}
	
	
	@PostMapping("/userProfiles/new")
	private UserProfile addNewUserprofile(@RequestBody UserProfile profile,@RequestParam(value = "userId")Long userId,@RequestParam(value = "desId", defaultValue=AppConstants.DEFAULT_DESIGNATION_ID)Long desId)
	{
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		profile.setUser(user);
		
		Designation designation = designationRepository.findById(desId)
				.orElseThrow(()-> new ResourceNotFoundException("Designation", "id", desId));
		profile.setDesignation(designation);
		
		return userProfileRepository.saveAndFlush(profile);
	}
	
	
	@GetMapping("/users/active/{isActive}")
	private List<User> getActiveUsers(@PathVariable(value = "isActive")String isActive)
	{
		return userProfileRepository.findActiveUsers(isActive);
	}
	
	
	@GetMapping("/userprofile/active/{isActive}")
	private List<UserProfile> getActiveUsersProfile(@PathVariable(value = "isActive")String isActive)
	{
		return userProfileRepository.findActiveUserProfile(isActive);
	}
	
	
	@GetMapping("/usersprofiles/all")
	private ResponseEntity<ResponseBean> getAllUserProfiles()
	{
		List<UserProfile> users = new ArrayList<UserProfile>();
		List<UserProfileResponse> results = new ArrayList<UserProfileResponse>();
		users = userProfileRepository.findAll();
		users.forEach(user ->{
			results.add(new UserProfileResponse(user,user.getDesignation(),user.getUser()));
		});
		ResponseBean response = new ResponseBean(results);
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	

}
