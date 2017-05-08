package com.arun.service;

import com.arun.request.RegisterUserRequest;
import com.arun.response.UserDetailsResponse;

public interface RegisterService {
	
	void registerUser(RegisterUserRequest registerUser);
	
	UserDetailsResponse getUserDetails(String userId);
	
}
