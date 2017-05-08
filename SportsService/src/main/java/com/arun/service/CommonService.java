package com.arun.service;

import java.util.List;
import com.arun.domain.Shoes;
import com.arun.dto.ShoeDTO;

public interface CommonService {

	List<ShoeDTO> getShoeDTO(List<Shoes> allShoes);
}
