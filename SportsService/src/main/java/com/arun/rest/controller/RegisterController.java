package com.arun.rest.controller;

import org.jsondoc.core.annotation.ApiHeader;
import org.jsondoc.core.annotation.ApiHeaders;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RestController;

import com.arun.dto.StatusResponseDTO;
import com.arun.logging.Loggable;
import com.arun.request.RegisterUserRequest;
import com.arun.response.UserDetailsResponse;
import com.arun.service.RegisterService;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RestController 
@RequestMapping(value = "/register")
public class RegisterController {  
		
	@Autowired
	private RegisterService registerService; 
	
	@Loggable
	@ApiMethod(description = "Add user", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public StatusResponseDTO addUser(@RequestBody RegisterUserRequest registerUser) {
	  		registerService.registerUser(registerUser);
	  		StatusResponseDTO statusDTO = new StatusResponseDTO();
			statusDTO.setStatusCode(0L);
			statusDTO.setStatusMessage("Success");
			return statusDTO;
	}
	
	@Loggable
	@ApiMethod(description = "Fetch user", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/fetchUser", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public UserDetailsResponse fetchUser(@RequestParam(value = "userId", required = true) final String userId) {
	  		return registerService.getUserDetails(userId);
	}
	
	
}  