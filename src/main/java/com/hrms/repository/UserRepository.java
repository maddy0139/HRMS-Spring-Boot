package com.hrms.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.hrms.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	//@Query("from User u where u.isActive=:active")
	//List<User> findActiveUsers(@Param("active") String isActive);
	
}
