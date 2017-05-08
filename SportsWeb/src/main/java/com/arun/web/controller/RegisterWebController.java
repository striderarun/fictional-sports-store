package com.arun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus; 

import com.arun.dto.StatusResponseDTO;
import com.arun.logging.Loggable;
import com.arun.request.AddShoeRequest;
import com.arun.request.RegisterUserRequest;
import com.arun.response.UserDetailsResponse;
import com.arun.web.repository.MySportsRepository;


@Controller 
@RequestMapping(value = "/register")
public class RegisterWebController {  
	
	@Autowired
	private MySportsRepository sportsRepository; 
	
	@Loggable
	@RequestMapping(value = "/checkUserId", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public StatusResponseDTO checkUserId(@RequestBody AddShoeRequest addShoe) {
			StatusResponseDTO statusDTO = sportsRepository.addShoe(addShoe);
			return statusDTO;
	}
	
	@Loggable
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public StatusResponseDTO addUser(@RequestBody RegisterUserRequest registerUser) {
			StatusResponseDTO statusDTO = sportsRepository.registerUser(registerUser);
			return statusDTO;
	}
	
	@Loggable
	@RequestMapping(value = "/fetchUser", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public UserDetailsResponse fetchUser(@RequestParam(value = "userId", required = true) final String userId) {
			UserDetailsResponse userDetails = sportsRepository.fetchUser(userId);
			return userDetails;
	}
	
	
}  