package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

import ch.qos.logback.core.joran.util.beans.BeanUtil;

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
	private ResponseEntity<ResponseBean> getUserProfile(@PathVariable(value = "id")Long id)
	{
		UserProfile profile = userProfileRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("UserProfile", "id", id));
		
		ResponseBean response = new ResponseBean(new UserProfileResponse(profile, profile.getDesignation(), profile.getUser()));
		
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	
	@GetMapping("/userProfiles/user/{id}")
	private ResponseEntity<ResponseBean> getUserProfileByUserId(@PathVariable(value = "id")Long id)
	{
		UserProfile profile = userProfileRepository.findByUser_Id(id)
				.orElseThrow(()->new ResourceNotFoundException("UserProfile", "id", id));
		
		ResponseBean response = new ResponseBean(new UserProfileResponse(profile, profile.getDesignation(), profile.getUser()));
		
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	
	@PostMapping("/userProfiles/new")
	private ResponseEntity<ResponseBean> addNewUserprofile(@RequestBody UserProfile profile,@RequestParam(value = "userId")Long userId,@RequestParam(value = "desId", defaultValue=AppConstants.DEFAULT_DESIGNATION_ID)Long desId)
	{
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		profile.setUser(user);
		
		Designation designation = designationRepository.findById(desId)
				.orElseThrow(()-> new ResourceNotFoundException("Designation", "id", desId));
		profile.setDesignation(designation);
		
		UserProfile res = userProfileRepository.saveAndFlush(profile);
		ResponseBean response = new ResponseBean(new UserProfileResponse(res, res.getDesignation(), res.getUser()));
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/users/active/{isActive}")
	private ResponseEntity<ResponseBean> getActiveUsers(@PathVariable(value = "isActive")String isActive)
	{
		List<User> users = new ArrayList<User>();
		users = userProfileRepository.findActiveUsers(isActive);
		ResponseBean response = new ResponseBean(users);
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/userProfiles/active/{isActive}")
	private ResponseEntity<ResponseBean> getActiveUsersProfile(@PathVariable(value = "isActive")String isActive)
	{
		List<UserProfile> users = new ArrayList<UserProfile>();
		List<UserProfileResponse> results = new ArrayList<UserProfileResponse>();
		users = userProfileRepository.findActiveUserProfile(isActive);
		users.forEach(user ->{
			results.add(new UserProfileResponse(user,user.getDesignation(),user.getUser()));
		});
		ResponseBean response = new ResponseBean(results);
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/userProfiles/all")
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
	
	@PutMapping("/userProfiles/user/{id}")
	private ResponseEntity<ResponseBean> updateUserProfileByUserId(@RequestParam(value="desId")Long desId ,@PathVariable(value="id")Long id,@RequestBody UserProfile profileDetails)
	{
		UserProfile profile = userProfileRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		
		profile.setFirstName(profileDetails.getFirstName());
		profile.setLastName(profileDetails.getLastName());
		profile.setGender(profileDetails.getGender());
		profile.setIsActive(profileDetails.getIsActive());
		profile.setDesignation(new Designation(profileDetails.getDesignation().getId(),profileDetails.getDesignation().getDesignation()));
		
		User user = new User();
		user.setId(id);
		profile.setUser(user);
		UserProfile res = userProfileRepository.saveAndFlush(profile);
		UserProfileResponse result = new UserProfileResponse(res, res.getDesignation(), res.getUser());
		ResponseBean response = new ResponseBean(result);
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
		
	}
	

}
