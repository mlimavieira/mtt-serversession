package com.mttnow.session;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mttnow.session.mongo.model.Beer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BeerControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getTest() {
		Beer[] beers = restTemplate.getForObject("/beer", Beer[].class);
		Assert.assertNotNull(beers);
	}

	@Test
	public void postTest() {

		Beer beer = new Beer();
		beer.setName("Test" + RandomStringUtils.randomAlphanumeric(6));
		beer.setDescription("Test description");
		
		Beer savedBeer = restTemplate.postForObject("/beer", beer, Beer.class);
		
		Assert.assertNotNull(savedBeer);
		Assert.assertNotNull(savedBeer.getId());
	}

}
