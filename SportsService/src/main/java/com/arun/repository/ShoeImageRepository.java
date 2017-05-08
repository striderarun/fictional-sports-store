package com.arun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arun.domain.ShoeImage;


public interface ShoeImageRepository extends JpaRepository<ShoeImage, Integer> {
	
	ShoeImage findByShoeId(Long shoeId);


}
