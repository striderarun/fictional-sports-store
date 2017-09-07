package com.arun.rest.controller;


import java.util.List;

import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiHeader;
import org.jsondoc.core.annotation.ApiHeaders;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arun.common.FilterDefinition;
import com.arun.domain.Shoes;
import com.arun.domain.UserCreds;
import com.arun.dto.ShoeDTO;
import com.arun.dto.ShoeImageDTO;
import com.arun.dto.StatusResponseDTO;
import com.arun.logging.Loggable;
import com.arun.request.AddShoeRequest;
import com.arun.request.UpdateShoeRequest;
import com.arun.response.ShoeListResponse;
import com.arun.service.RunService;
import com.arun.specification.ShoeSpecification;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RestController
@RequestMapping(value = "/shoes")
public class RunController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RunController.class);
	private static final String SUCCESS = "Success";
	
	@Autowired
	private RunService runService;

	@Autowired
	private Environment env;

	@Loggable
	@ApiMethod(description = "Get All Shoes", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/getAllShoes", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ShoeListResponse getAllShoes() {
			LOGGER.info("Reading from application file" + env.getProperty("server.contextPath"));
	  		ShoeListResponse shoeList = new ShoeListResponse();
	  		List<ShoeDTO> allShoes = runService.getAllShoes();
 	  		shoeList.setAaData(allShoes);
	  		return shoeList;
	}
	
	/**
	 * Single Method to accept multiple optional parameters
	 * @param filter
	 * @return
	 */
	@Loggable
	@ApiMethod(description = "Filter shoes", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/getShoesFilter", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ShoeListResponse getShoesFilter(@FilterDefinition(paths = ShoeSpecification.class) Specification<Shoes> filter) {
	  		ShoeListResponse shoeList = new ShoeListResponse();
	  		List<ShoeDTO> allShoes = runService.getShoesFilter(filter);
 	  		shoeList.setAaData(allShoes);
	  		return shoeList;
	}
	
	@Loggable
	@ApiMethod(description = "Paginated shoes list", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/getAllShoesPage", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Page<ShoeDTO> getAllShoesPaginated(@PageableDefault(page = 0, size = 5, sort = {"brandName"}, direction = Direction.ASC) Pageable pageRequest) {
			LOGGER.info("Reading from application file" + env.getProperty("server.contextPath"));
	  		Page<ShoeDTO> allShoes = runService.getAllShoesPaginated(pageRequest);
	  		return allShoes;
	}

	@Loggable
	@ApiMethod(description = "Get shoes by brand", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/getShoesByBrand", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ShoeListResponse getShoesByBrand(@RequestParam(value = "brandName", required = true) final String brandName) {
	  		ShoeListResponse shoeList = new ShoeListResponse();
	  		List<ShoeDTO> allShoes = runService.getShoesByBrand(brandName);
 	  		shoeList.setAaData(allShoes);
	  		return shoeList;
	}

	@Loggable
	@ApiMethod(description = "Get shoes by brand and price", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/getShoesByBrandAndPrice", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ShoeListResponse getShoesByBrandAndPrice(@RequestParam(value = "brandName", required = true) final String brandName,@RequestParam(value = "lowerPrice", required = true) final String lowerPrice, @RequestParam(value = "upperPrice", required = true) final String upperPrice) {
			ShoeListResponse shoeList = new ShoeListResponse();
			List<ShoeDTO> allShoes = runService.getShoesByBrandAndPrice(brandName, Long.valueOf(upperPrice), Long.valueOf(lowerPrice));
			shoeList.setAaData(allShoes);
	  		return shoeList;
	}
	
	@Loggable
	@ApiMethod(description = "Get shoes by brand and price specification", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/getShoesByBrandAndPriceSpec", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ShoeListResponse getShoesByBrandAndPriceSpec(@RequestParam(value = "brandName", required = true) final String brandName,@RequestParam(value = "price", required = true) final String price) {
			ShoeListResponse shoeList = new ShoeListResponse();
			List<ShoeDTO> allShoes = runService.getShoesByBrandAndPriceSpecification(brandName, Long.valueOf(price));
			shoeList.setAaData(allShoes);
	  		return shoeList;
	}


	@Loggable
	@ApiMethod(description = "Get shoes by brand or price specification", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/getShoesByBrandOrPrice", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ShoeListResponse getShoesByBrandOrPrice(@RequestParam(value = "brandName", required = true) final String brandName,@RequestParam(value = "lowerPrice", required = true) final String lowerPrice, @RequestParam(value = "upperPrice", required = true) final String upperPrice) {
			ShoeListResponse shoeList = new ShoeListResponse();
			List<ShoeDTO> allShoes = runService.getShoesByBrandOrPrice(brandName, Long.valueOf(upperPrice), Long.valueOf(lowerPrice));
			shoeList.setAaData(allShoes);
	  		return shoeList;
	}

	@Loggable
	@ApiMethod(description = "Add shoe", responsestatuscode = "201 - Created")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/addShoe", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public StatusResponseDTO addShoe(@ApiBodyObject @RequestBody AddShoeRequest addShoe) {
			runService.addShoe(addShoe);
			StatusResponseDTO statusDTO = new StatusResponseDTO();
			statusDTO.setStatusCode(0L);
			statusDTO.setStatusMessage(SUCCESS);
			return statusDTO;
	}
	
	@Loggable
	@ApiMethod(description = "Authenticate", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public StatusResponseDTO authenticate(@RequestParam(value = "userName", required = true) final String userName, @RequestParam(value = "password", required = true) final String password) {
		UserCreds user = runService.authenticateUser(userName, password);
		StatusResponseDTO statusDTO = new StatusResponseDTO();
		if (null != user) {
			LOGGER.info("Role" + user.getUserDetails().getRole());
			statusDTO.setStatusCode(0L);
			statusDTO.setStatusMessage("success");
			statusDTO.setRole(user.getUserDetails().getRole());
		} else {
			statusDTO.setStatusCode(1L);
			statusDTO.setStatusMessage("failure");
		}
		return statusDTO;
	}
	
	@Loggable
	@ApiMethod(description = "Update shoe", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/updateShoes", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public StatusResponseDTO updateShoe(@ApiBodyObject @RequestBody List<UpdateShoeRequest> updateShoes) {
			runService.updateShoe(updateShoes);
			StatusResponseDTO statusDTO = new StatusResponseDTO();
			statusDTO.setStatusCode(0L);
			statusDTO.setStatusMessage(SUCCESS);
			return statusDTO;
	}
	
	@Loggable
	@ApiMethod(description = "Delete shoe", responsestatuscode = "201 - Created")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/deleteShoes", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public StatusResponseDTO delete(@ApiBodyObject @RequestBody UpdateShoeRequest deleteShoe) {
			runService.deleteShoe(deleteShoe);
			StatusResponseDTO statusDTO = new StatusResponseDTO();
			statusDTO.setStatusCode(0L);
			statusDTO.setStatusMessage(SUCCESS);
			return statusDTO;
	}

	@Loggable
	@ApiMethod(description = "Upload shoe image", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/uploadShoeImage", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public StatusResponseDTO uploadImage(@ApiBodyObject @RequestBody ShoeImageDTO shoeImageDTO) {
		runService.uploadShoeImage(shoeImageDTO);
		StatusResponseDTO statusDTO = new StatusResponseDTO();
		statusDTO.setStatusCode(0L);
		statusDTO.setStatusMessage(SUCCESS);
		return statusDTO;
	}
	
	@Loggable
	@ApiMethod(description = "Fetch shoe image", responsestatuscode = "200 - OK")
	@ApiHeaders(headers = {
			@ApiHeader(name = ACCEPT),
			@ApiHeader(name = CONTENT_TYPE)
	})
	@ApiResponseObject
	@RequestMapping(value = "/fetchShoeImage", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ShoeImageDTO fetchShoeImage(@ApiQueryParam(name = "shoeId") @RequestParam(value = "shoeId", required = true) final Long shoeId) {
		return runService.fetchShoeImage(shoeId);
	}
	
}
