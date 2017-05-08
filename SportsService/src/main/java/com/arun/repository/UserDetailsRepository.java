package com.arun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arun.domain.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
	
	UserDetails findByUserId(String userId);


}
