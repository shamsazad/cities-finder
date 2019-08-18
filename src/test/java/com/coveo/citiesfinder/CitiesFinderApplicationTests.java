package com.coveo.citiesfinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CitiesFinderApplication.class})
@ComponentScan(basePackages = {"com.coveo.citiesfinder"})
public class CitiesFinderApplicationTests {

	@Test
	public void contextLoads() {
	}

}
