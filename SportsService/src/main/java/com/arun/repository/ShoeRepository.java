package com.arun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.arun.domain.Shoes;

public interface ShoeRepository extends JpaRepository<Shoes, Integer>, JpaSpecificationExecutor<Shoes> {
	
	List<Shoes> findByBrandName(String brandName);
	
	List<Shoes> findByBrandNameAndPriceLessThanAndPriceGreaterThan(String brandName,Long upperPrice,Long lowerPrice);
	
	List<Shoes> findByBrandNameOrPriceLessThanAndPriceGreaterThan(String brandName,Long upperPrice,Long lowerPrice);
	


}
