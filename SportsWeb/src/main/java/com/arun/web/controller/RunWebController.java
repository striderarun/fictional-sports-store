package com.arun.web.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.arun.dto.AggregateDTO;
import com.arun.dto.SessionDTO;
import com.arun.dto.ShoeImageDTO;
import com.arun.dto.StatusResponseDTO;
import com.arun.logging.Loggable;
import com.arun.request.AddShoeRequest;
import com.arun.request.UpdateShoeRequest;
import com.arun.response.ShoeListResponse;
import com.arun.response.ShoeNameResponse;
import com.arun.util.CommonUtil;
import com.arun.web.repository.MySportsRepository;


@Controller
@RequestMapping(value = "/shoes")
public class RunWebController {


	@Autowired
	private MySportsRepository sportsRepository;

	@Loggable
	@RequestMapping(value = "/getAllShoes", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@PreAuthorize("hasRole('Admin') or hasRole('Owner')")
	public ShoeListResponse getAllShoes(HttpServletRequest request) {
			System.out.println("Interceptor value" + request.getAttribute("interceptor"));
			SessionDTO sessionDTO = CommonUtil.getSessionBean(request);
//			System.out.println("Password from session"+ sessionDTO.getPassword());
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie: cookies) {
				System.out.println("Cookie Name" + cookie.getName());
				System.out.println("Cookie Value" + cookie.getValue());
			}
	  		ShoeListResponse allShoes = sportsRepository.getAllShoes();
 	  		return allShoes;
	}

	@Loggable
	@RequestMapping(value = "/getShoesByBrand", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ShoeListResponse getShoesByBrand(@RequestParam(value = "brandName", required = true) String brandName) {
	  		ShoeListResponse allShoes = sportsRepository.getShoesByBrand(brandName);
	  		return allShoes;
	}

	@Loggable
	@RequestMapping(value = "/getShoesByBrandAndPrice", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ShoeListResponse getShoesByBrandAndPrice(@RequestParam(value = "brandName", required = true) final String brandName,@RequestParam(value = "lowerPrice", required = true) final String lowerPrice, @RequestParam(value = "upperPrice", required = true) final String upperPrice) {
			ShoeListResponse allShoes = sportsRepository.getShoesByBrandAndPrice(brandName, Long.valueOf(upperPrice), Long.valueOf(lowerPrice));
	  		return allShoes;
	}

	@Loggable
	@RequestMapping(value = "/getShoesByBrandOrPrice", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 3000))
	public ShoeListResponse getShoesByBrandOrPrice(@RequestParam(value = "brandName", required = true) final String brandName,@RequestParam(value = "lowerPrice", required = true) final String lowerPrice, @RequestParam(value = "upperPrice", required = true) final String upperPrice) {
			ShoeListResponse allShoes = sportsRepository.getShoesByBrandOrPrice(brandName, Long.valueOf(upperPrice), Long.valueOf(lowerPrice));
	  		return allShoes;
	}

	@Loggable
	@RequestMapping(value = "/addShoe", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public StatusResponseDTO addShoe(@RequestBody AddShoeRequest addShoe) {
			StatusResponseDTO statusDTO = sportsRepository.addShoe(addShoe);
			return statusDTO;
	}

	@Loggable
	@RequestMapping(value = "/updateShoes", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public StatusResponseDTO updateShoe(@RequestBody List<UpdateShoeRequest> updateShoes) {
			StatusResponseDTO statusDTO = sportsRepository.updateShoe(updateShoes);
			return statusDTO;
	}

	@Loggable
	@RequestMapping(value = "/deleteShoes", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public StatusResponseDTO delete(@RequestBody UpdateShoeRequest deleteShoe) {
			StatusResponseDTO statusDTO = sportsRepository.deleteShoe(deleteShoe);
			return statusDTO;
	}

	@Loggable
	@RequestMapping(value = "/getShoeProjections", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ShoeNameResponse getShoeProjections() {
		ShoeNameResponse allShoes = sportsRepository.getShoeProjections();
		return allShoes;
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
    public String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                ShoeImageDTO shoeImageDTO = new ShoeImageDTO();
                shoeImageDTO.setShoeId(1080L);
                shoeImageDTO.setShoeImage(bytes);
                sportsRepository.uploadShoeImage(shoeImageDTO);
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

	@RequestMapping(value="/multiUpload", method=RequestMethod.POST)
	@ResponseBody
    public String handleMultiFileUpload(@RequestParam("name") String[] names, @RequestParam("file") MultipartFile[] files){
		String message = null;
		if(names.length != files.length) {
			message = "Wrong upload";
		}
        for (int i=0; i< files.length; i++) {
        	if (!files[i].isEmpty()) {
        		try {
        			byte[] bytes = files[i].getBytes();
        			File file = new File(names[i]);
        			FileOutputStream fileOutputStream = new FileOutputStream(file);
        			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        			bufferedOutputStream.write(bytes);
        			bufferedOutputStream.close();
        			message = "Succesfully uploaded file" + names[i];
        		} catch (Exception e) {
        			message = "You failed to upload " + names[i] + " => " + e.getMessage();
        		}
        	} else {
        		message = "File was empty";
        	}
        }
        return message;
    }
	
	@Loggable
	@ResponseBody
	@RequestMapping(value = "/fetchShoeImage", method = RequestMethod.GET, produces = "image/png")
	public byte[] getShoeImage(@RequestParam(value = "shoeId", required = true) final Long shoeId) {
		return sportsRepository.fetchShoeImage(shoeId);
	}
}