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
import com.hrms.model.User;
import com.hrms.model.UserAddress;
import com.hrms.payload.AddressResponse;
import com.hrms.payload.ResponseBean;
import com.hrms.repository.AddressRepository;

@RestController
@RequestMapping("/api")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping("/address/all")
	private ResponseEntity<ResponseBean> getAllAddress()
	{
		List<UserAddress> addresses = addressRepository.findAll();
		List<AddressResponse> allAddress = new ArrayList<AddressResponse>();
		addresses.forEach(address ->{
			allAddress.add(new AddressResponse(address));
		});
		ResponseBean response = new ResponseBean(allAddress);
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	
	@GetMapping("/address/user/{id}")
	private ResponseEntity<ResponseBean> getAddressByUserId(@PathVariable(value = "id")Long id)
	{

		List<UserAddress> addresses = addressRepository.findByUser_Id(id);
		List<AddressResponse> allAddress = new ArrayList<AddressResponse>();
		addresses.forEach(address ->{
			allAddress.add(new AddressResponse(address));
		});
		ResponseBean response = new ResponseBean(allAddress);
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
	
	@PostMapping("/address/new")
	private ResponseEntity<ResponseBean> addNewAddress(@RequestBody UserAddress address, @RequestParam(value = "userId")Long id)
	{
		User user = new User();
		user.setId(id);
		address.setUser(user);
		UserAddress res = addressRepository.saveAndFlush(address);
		ResponseBean response = new ResponseBean(new AddressResponse(res));
		return new ResponseEntity<ResponseBean>(response,HttpStatus.OK);
	}
}
