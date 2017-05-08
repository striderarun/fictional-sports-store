package com.arun.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.arun.domain.Shoes;
import com.arun.domain.UserCreds;
import com.arun.dto.ShoeDTO;
import com.arun.dto.ShoeImageDTO;
import com.arun.request.AddShoeRequest;
import com.arun.request.UpdateShoeRequest;

public interface RunService {

	void addShoe(AddShoeRequest addShoeRequest);
	
	
	List<ShoeDTO> getAllShoes();
	
	List<ShoeDTO> getShoesFilter(Specification<Shoes> filter);
	
	Page<ShoeDTO> getAllShoesPaginated(Pageable pageRequest);
	
	List<ShoeDTO> getShoesByBrand(String brandName);
	
	List<ShoeDTO> getShoesByBrandAndPrice(String brandName,Long upperPrice,Long lowerPrice);
	
	List<ShoeDTO> getShoesByBrandOrPrice(String brandName,Long upperPrice,Long lowerPrice);
	
	List<ShoeDTO> getShoesByBrandAndPriceSpecification(String brandName,Long price);
	
	UserCreds authenticateUser(String userName, String password);
	
	void updateShoe(List<UpdateShoeRequest> updateShoes);
	
	void deleteShoe(UpdateShoeRequest deleteShoes);
	
	void uploadShoeImage(ShoeImageDTO shoeImageDTO);
	
	ShoeImageDTO fetchShoeImage(Long shoeId);
		
}
