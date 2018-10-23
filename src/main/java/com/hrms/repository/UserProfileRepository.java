package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.model.User;
import com.hrms.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	@Query("select user from User user LEFT JOIN UserProfile up ON up.user.id = user.id where user.isActive=:active and up.isActive=:active")
	List<User> findActiveUsers(@Param("active") String isActive);
	
	@Query("select up from UserProfile up where up.user.isActive=:active and up.isActive=:active")
	List<UserProfile> findActiveUserProfile(@Param("active") String isActive);
}
