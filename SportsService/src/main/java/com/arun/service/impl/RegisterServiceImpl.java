package com.arun.service.impl;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.domain.UserAddress;
import com.arun.domain.UserCreds;
import com.arun.domain.UserDetails;
import com.arun.domain.UserEmail;
import com.arun.domain.UserPhone;
import com.arun.logging.Loggable;
import com.arun.repository.UserDetailsRepository;
import com.arun.request.RegisterUserRequest;
import com.arun.response.UserDetailsResponse;
import com.arun.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Resource
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Loggable
	@Override
	public void registerUser(RegisterUserRequest registerUser) {
		UserAddress userAddress = mapper.map(registerUser, UserAddress.class);
		UserCreds userCreds 	= mapper.map(registerUser, UserCreds.class);
		UserDetails userDetails = mapper.map(registerUser, UserDetails.class);
		
		userDetails.setUserAddress(userAddress);
		
		UserPhone userPhone1 = new UserPhone();
		userPhone1.setPhoneNo(registerUser.getPhoneOne());
		userPhone1.setUserDetails(userDetails);
		
		UserPhone userPhone2 = new UserPhone();
		userPhone2.setPhoneNo(registerUser.getPhoneTwo());
		userPhone2.setUserDetails(userDetails);
		
		Set<UserPhone> userPhones = new HashSet<UserPhone>();
		userPhones.add(userPhone1);
		userPhones.add(userPhone2);
		
		UserEmail userEmail1 = new UserEmail();
		userEmail1.setEmail(registerUser.getEmailOne());
		userEmail1.setUserDetails(userDetails);
		
		UserEmail userEmail2 = new UserEmail();
		userEmail2.setEmail(registerUser.getEmailTwo());
		userEmail2.setUserDetails(userDetails);
		
		Set<UserEmail> userEmails = new HashSet<UserEmail>();
		userEmails.add(userEmail1);
		userEmails.add(userEmail2);
		
		userDetails.setUserEmails(userEmails);
		userDetails.setUserPhones(userPhones);
		userDetails.setUserCreds(userCreds);
		userCreds.setUserDetails(userDetails);
		
		userDetailsRepository.save(userDetails);
		
	}
	
	@Loggable
	@Override
	public UserDetailsResponse getUserDetails(String userId) {
		
		UserDetails userDetails = userDetailsRepository.findByUserId(userId);
		UserDetailsResponse userDetailsResponse = mapper.map(userDetails, UserDetailsResponse.class);
		
		if (null != userDetails.getUserAddress()) {
			userDetailsResponse.setCity(userDetails.getUserAddress().getCity());
			userDetailsResponse.setState(userDetails.getUserAddress().getState());
			userDetailsResponse.setCountry(userDetails.getUserAddress().getCountry());
			userDetailsResponse.setZipCode(userDetails.getUserAddress().getZipCode());
		}
		
		
		Iterator<UserEmail> iterator = userDetails.getUserEmails().iterator();
		if (iterator.hasNext()) {
			userDetailsResponse.setEmailOne(iterator.next().getEmail());
			if(iterator.hasNext()) {
				userDetailsResponse.setEmailTwo(iterator.next().getEmail());
			}
		}
		
		Iterator<UserPhone> phoneIterator = userDetails.getUserPhones().iterator();
		if (phoneIterator.hasNext()) {
			userDetailsResponse.setPhoneOne(phoneIterator.next().getPhoneNo());
			if (phoneIterator.hasNext()) {
				userDetailsResponse.setPhoneTwo(phoneIterator.next().getPhoneNo());
			}
		}
		return userDetailsResponse;
	}
	
}