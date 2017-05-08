package com.arun.web.repository;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.arun.dto.ShoeImageDTO;
import com.arun.dto.StatusResponseDTO;
import com.arun.logging.Loggable;
import com.arun.request.AddShoeRequest;
import com.arun.request.RegisterUserRequest;
import com.arun.request.UpdateShoeRequest;
import com.arun.response.ShoeListResponse;
import com.arun.response.ShoeNameResponse;
import com.arun.response.UserDetailsResponse;
import com.arun.util.SecurityUtil;

@Repository("mySportsRepository")
public class MySportsRepositoryImpl implements MySportsRepository{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment appProperties;
	
	public StatusResponseDTO registerUser(RegisterUserRequest registerUser){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("register.url");
		HttpHeaders headers = getCommonHeaders();
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(registerUser, headers);
		return invokePost(aRequestURL, httpEntity, StatusResponseDTO.class);
	}
	
	public StatusResponseDTO authenticateUser(String userName, String password){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("authenticate.url") + "?userName="+userName+"&password="+password;
		final StatusResponseDTO isAuthenticated = invokeGet(aRequestURL, StatusResponseDTO.class);
		return isAuthenticated;
	}

	public StatusResponseDTO addShoe(AddShoeRequest addShoeRequest){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("addShoe.url");
		HttpHeaders headers = getCommonHeaders();
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(addShoeRequest, headers);
		return invokePost(aRequestURL, httpEntity, StatusResponseDTO.class);
	}
	
	public StatusResponseDTO updateShoe(List<UpdateShoeRequest> updateShoes){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("updateShoes.url");
		HttpHeaders headers = getCommonHeaders();
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(updateShoes, headers);
		return invokePost(aRequestURL, httpEntity, StatusResponseDTO.class);
	}
	
	public StatusResponseDTO deleteShoe(UpdateShoeRequest deleteShoes){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("deleteShoes.url");
		HttpHeaders headers = getCommonHeaders();
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(deleteShoes, headers);
		return invokePost(aRequestURL, httpEntity, StatusResponseDTO.class);
	}
	
	public ShoeListResponse getAllShoes(){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("getAllShoes.url");
		return invokeGet(aRequestURL, ShoeListResponse.class);
		
	}
	
	public ShoeListResponse getShoesByBrand(String brandName){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("getShoesByBrand.url") + "?brandName=" + brandName;
		return invokeGet(aRequestURL, ShoeListResponse.class);
	}
	
	public ShoeListResponse getShoesByBrandAndPrice(String brandName,Long upperPrice,Long lowerPrice){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("getShoesByBrandAndPrice.url") + "?brandName="+brandName+"&lowerPrice="+lowerPrice+"&upperPrice="+upperPrice;
		final ShoeListResponse allShoes = invokeGet(aRequestURL, ShoeListResponse.class);
		return allShoes;
	}
	
	public ShoeListResponse getShoesByBrandOrPrice(String brandName,Long upperPrice,Long lowerPrice){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("getShoesByBrandOrPrice.url") + "?brandName="+brandName+"&lowerPrice="+lowerPrice+"&upperPrice="+upperPrice;
		final ShoeListResponse allShoes = invokeGet(aRequestURL, ShoeListResponse.class);
		return allShoes;
	}
	
	public ShoeNameResponse getShoeProjections(){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("getShoeProjections.url");
		HttpHeaders headers = getCommonHeaders();
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		return invokePost(aRequestURL, httpEntity, ShoeNameResponse.class);
	}
	
	public UserDetailsResponse fetchUser(String userId){
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("fetchUser.url") + "?userId="+userId;
		final UserDetailsResponse userDetails = invokeGet(aRequestURL, UserDetailsResponse.class);
		return userDetails;
	}
	
	public StatusResponseDTO uploadShoeImage(ShoeImageDTO shoeImageDTO) {
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("uploadShoeImage.url");
		HttpHeaders headers = getCommonHeaders();
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(shoeImageDTO, headers);
		return invokePost(aRequestURL, httpEntity, StatusResponseDTO.class);
	}
	
	public byte[] fetchShoeImage(Long shoeId) {
		final String aRequestURL = appProperties.getProperty("MySportsService.url") + appProperties.getProperty("fetchShoeImage.url") + "?shoeId="+shoeId;
		final ShoeImageDTO shoeImageDTO = invokeGet(aRequestURL, ShoeImageDTO.class);
		return shoeImageDTO.getShoeImage();
	}
	
//	public StatusResponseDTO bulkUploadShoe(InputStream inputStream) {
//		
//	}
//	
	/**
	 * Method - Call rest service with POST method
	 * 
	 * @param httpEntity - HTTP Entity
	 * @param url - Request URL
	 * @param responseType - Response Type
	 * @param uriVariables - URI Variables
	 * @param <T> - Request
	 * @return <T> - Response
	 */
	@Loggable
	private <T> T invokePost(final String url, final HttpEntity<Object> httpEntity, final Class<T> responseType, final Object... uriVariables) {
		return restTemplate.postForObject(url, httpEntity, responseType, uriVariables);
	}

	/**
	 * Method to get headers
	 * 
	 * @return HttpHeaders
	 */
	@Loggable
	private HttpHeaders getCommonHeaders() {
		String requestDateString = String.valueOf(Calendar.getInstance().getTimeInMillis());
		final HttpHeaders httpHeaders = new HttpHeaders();
		String hmac = SecurityUtil.calculateRFC2104HMACForString(requestDateString, "82f9f37fd83be96eabb23e15d7548505");
		httpHeaders.add("hmac", hmac);
		httpHeaders.add("timestamp", requestDateString);
		return httpHeaders;
	}
	
	/**
	 * Method - Call rest service with GET method
	 * 
	 * @param url - Request URL
	 * @param responseType - Response Type
	 * @param uriVariables - URI Variables
	 * @param <T> - Request
	 * @return <T> - Response
	 */
	@Loggable
	private <T> T invokeGet(final String url, final Class<T> responseType) {
		HttpHeaders httpHeaders = getCommonHeaders();
		ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<byte[]>(httpHeaders), responseType);
		return response.getBody();
	}
}
