package com.arun.web.repository;

import java.util.List;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import com.arun.dto.AggregateDTO;
import com.arun.dto.ShoeImageDTO;
import com.arun.dto.StatusResponseDTO;
import com.arun.request.AddShoeRequest;
import com.arun.request.RegisterUserRequest;
import com.arun.request.UpdateShoeRequest;
import com.arun.response.ShoeListResponse;
import com.arun.response.ShoeNameResponse;
import com.arun.response.UserDetailsResponse;

public interface MySportsRepository {

	StatusResponseDTO registerUser(RegisterUserRequest registerUser);
	
	StatusResponseDTO authenticateUser(String userName, String password);
	
	StatusResponseDTO addShoe(AddShoeRequest addShoeRequest);
	
	StatusResponseDTO updateShoe(List<UpdateShoeRequest> updateShoes);
	
	StatusResponseDTO deleteShoe(UpdateShoeRequest deleteShoes);
	
//	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 3000))
	ShoeListResponse getAllShoes();
	
	ShoeListResponse getShoesByBrand(String brandName);
	
	ShoeListResponse getShoesByBrandAndPrice(String brandName,Long upperPrice,Long lowerPrice);
	
	ShoeListResponse getShoesByBrandOrPrice(String brandName,Long upperPrice,Long lowerPrice);
	
	ShoeNameResponse getShoeProjections();
		
	UserDetailsResponse fetchUser(String userId);
	
	StatusResponseDTO uploadShoeImage(ShoeImageDTO shoeImageDTO);
	
	byte[] fetchShoeImage(Long shoeId);
	
//	StatusResponseDTO bulkUploadShoe(InputStream inputStream);
}
