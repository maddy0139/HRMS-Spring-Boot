package com.hrms.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	//@Query("from User u where u.isActive=:active")
	//List<User> findActiveUsers(@Param("active") String isActive);
	
}
