package com.arun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.domain.Shoes;
import com.arun.dto.ShoeDTO;
import com.arun.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService{

	@Autowired
	private Mapper mapper;
	
	@Override
	public List<ShoeDTO> getShoeDTO(List<Shoes> allShoes){
		List<ShoeDTO> allShoesDTO = new ArrayList<ShoeDTO>();
		for (Shoes shoe : allShoes) {
			ShoeDTO shoeDTO = mapper.map(shoe, ShoeDTO.class);
			allShoesDTO.add(shoeDTO);
		}
		return allShoesDTO;
	}
}
