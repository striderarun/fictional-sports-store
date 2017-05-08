package com.arun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arun.domain.UserCreds;

public interface UserRepository extends JpaRepository<UserCreds, Integer> {
	
	UserCreds findByUserIdAndPassword(String userId, String password);
	
	UserCreds findByUserId(String userId);


}
