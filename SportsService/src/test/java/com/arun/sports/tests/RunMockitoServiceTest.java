package com.arun.sports.tests;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arun.config.SportsConfig;
import com.arun.domain.Shoes;
import com.arun.dto.ShoeDTO;
import com.arun.repository.ShoeRepository;
import com.arun.service.CommonService;
import com.arun.service.RunService;
import com.arun.service.impl.CommonServiceImpl;
import com.arun.service.impl.RunServiceImpl;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SportsConfig.class})
@ActiveProfiles("test")
public class RunMockitoServiceTest {

	@InjectMocks
	RunService runService = new RunServiceImpl();
	
	@Mock
	ShoeRepository shoeTestRepository;
	
	@Mock
	CommonService commonService = new CommonServiceImpl();
	
	@Mock
	Mapper mapper = new DozerBeanMapper();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllShoesContent() {
		List<Shoes> listOfShoes= new ArrayList<Shoes>();
		List<ShoeDTO> listOfShoesDTO= new ArrayList<ShoeDTO>();

		Shoes shoes = new Shoes();
		shoes.setBrandName("Asics");
		shoes.setPrice(10000L);
		shoes.setShoeId(1L);
		shoes.setShoeName("GEL NIMBUS");
		listOfShoes.add(shoes);
		
		ShoeDTO shoeDTO = new ShoeDTO();
		shoeDTO.setBrandName("Asics");
		shoeDTO.setPrice(10000L);
		shoeDTO.setShoeId(1L);
		shoeDTO.setShoeName("GEL NIMBUS");
		listOfShoesDTO.add(shoeDTO);
				
		when(shoeTestRepository.findAll()).thenReturn(listOfShoes);
		when(mapper.map(shoes, ShoeDTO.class)).thenReturn(shoeDTO);
		when(commonService.getShoeDTO(listOfShoes)).thenReturn(listOfShoesDTO);
		List<ShoeDTO> list = runService.getAllShoes();
		ShoeDTO shoeBean = list.get(0);
		Assert.assertEquals("GEL NIMBUS", shoeBean.getShoeName() );
	}
}
