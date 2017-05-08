package com.arun.sports.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arun.config.SportsConfig;
import com.arun.dto.ShoeDTO;
import com.arun.service.RunService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SportsConfig.class)
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:setupDb.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:teardownDb.sql") 
})
@ActiveProfiles("test")
public class RunServiceTest {

	@Autowired
	private RunService runService;
	
	@Test
	public void getAllShoesSize() {
		List<ShoeDTO> shoes = runService.getAllShoes();
		Assert.assertEquals(shoes.size(), 1);
	}
	
	@Test
	public void getAllShoesContent() {
		List<ShoeDTO> shoes = runService.getAllShoes();
		ShoeDTO shoeDTO = shoes.get(0);
		Assert.assertEquals(shoeDTO.getShoeName(), "GEL NIMBUS");
	}
}
