package com.arun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.domain.ShoeImage;
import com.arun.domain.Shoes;
import com.arun.domain.UserCreds;
import com.arun.dto.ShoeDTO;
import com.arun.dto.ShoeImageDTO;
import com.arun.logging.Loggable;
import com.arun.repository.ShoeImageRepository;
import com.arun.repository.ShoeRepository;
import com.arun.repository.UserRepository;
import com.arun.request.AddShoeRequest;
import com.arun.request.UpdateShoeRequest;
import com.arun.service.CommonService;
import com.arun.service.RunService;
import com.arun.specification.ShoeSpecification;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;


@Service
@CacheConfig(cacheNames = "messages")
public class RunServiceImpl implements RunService {

	@Resource
	private ShoeRepository shoeRepository;

	@Resource
	private UserRepository userRepository;
	
	@Resource
	private ShoeImageRepository shoeImageRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private CommonService commonService;

	@Loggable
	@Override
	public void addShoe(AddShoeRequest addShoeRequest) {
		Shoes shoes = mapper.map(addShoeRequest, Shoes.class);
		shoeRepository.save(shoes);
	}

	@Loggable
	@Override
	public List<ShoeDTO> getAllShoes() {
		List<Shoes> allShoes = shoeRepository.findAll();
		return commonService.getShoeDTO(allShoes);
	}
	
	@Loggable
	@Override
	public List<ShoeDTO> getShoesFilter(Specification<Shoes> filter) {
		List<Shoes> allShoes = shoeRepository.findAll(filter);
		return commonService.getShoeDTO(allShoes);
	}
	
	/**Paginated Spring Data JPA repository query
	 * 
	 */
	@Loggable
	@Override
	@Cacheable 
	public Page<ShoeDTO> getAllShoesPaginated(Pageable pageRequest) {
		Page<Shoes> allShoes = shoeRepository.findAll(pageRequest);
		List<ShoeDTO> shoeDTOList = commonService.getShoeDTO(allShoes.getContent());
		PageImpl<ShoeDTO> shoeDTOPage = new PageImpl<ShoeDTO>(shoeDTOList);
		return shoeDTOPage;
	}

	@Loggable
	@Override
	@Cacheable
	public List<ShoeDTO> getShoesByBrand(String brandName) {
		List<Shoes> allShoes = shoeRepository.findByBrandName(brandName);
		return commonService.getShoeDTO(allShoes);
	}

	@Loggable
	@Override
	public List<ShoeDTO> getShoesByBrandAndPrice(String brandName,Long upperPrice, Long lowerPrice) {
		List<Shoes> allShoes = shoeRepository.findByBrandNameAndPriceLessThanAndPriceGreaterThan(brandName,upperPrice, lowerPrice);
		return commonService.getShoeDTO(allShoes);
	}
	
	/**
	 * JPA Specification
	 */
	@Loggable
	@Override
	public List<ShoeDTO> getShoesByBrandAndPriceSpecification(String brandName,Long price) {
		List<Shoes> allShoes = shoeRepository.findAll(Specifications.where(ShoeSpecification.brandSpecification(brandName)).and(ShoeSpecification.priceSpecification(price)));
		return commonService.getShoeDTO(allShoes);
	}

	@Loggable
	@Override
	public List<ShoeDTO> getShoesByBrandOrPrice(String brandName, Long upperPrice, Long lowerPrice) {
		List<Shoes> allShoes = shoeRepository.findByBrandNameOrPriceLessThanAndPriceGreaterThan(brandName,upperPrice, lowerPrice);
		return commonService.getShoeDTO(allShoes);
	}

	@Loggable
	@Override
	public UserCreds authenticateUser(String userName, String password) {
		return userRepository.findByUserIdAndPassword(userName, password);
	}

	@Loggable
	@Override
	public void updateShoe(List<UpdateShoeRequest> updateShoes) {
		for (UpdateShoeRequest updateShoe : updateShoes) {
			Shoes shoes = mapper.map(updateShoe, Shoes.class);
			shoeRepository.save(shoes);
		}
	}
	
	@Loggable
	@Override
	public void deleteShoe(UpdateShoeRequest deleteShoes) {
		Shoes shoes = mapper.map(deleteShoes, Shoes.class);
		shoeRepository.delete(shoes);
	}
	
	@Loggable
	@Override
	public void uploadShoeImage(ShoeImageDTO shoeImageDTO) {
		ShoeImage shoeImage = mapper.map(shoeImageDTO, ShoeImage.class);
		shoeImageRepository.save(shoeImage);
	}
	
	@Loggable
	@Override
	public ShoeImageDTO fetchShoeImage(Long shoeId) {
		ShoeImage shoeImage = shoeImageRepository.findByShoeId(shoeId);
		return mapper.map(shoeImage, ShoeImageDTO.class);
	}

}