package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.model.UserAddress;

public interface AddressRepository extends JpaRepository<UserAddress, Long>{
	List<UserAddress> findByUser_Id(Long id);
}
