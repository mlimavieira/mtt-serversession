package com.mttnow.session;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mttnow.session.mongo.model.Beer;

import redis.embedded.RedisServer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BeerControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private static RedisServer redisServer;

	@BeforeClass
	public static void setup() throws IOException {
		redisServer = new RedisServer(6379);
		redisServer.start();
	}
	
	@AfterClass
	public static void teardown() {
		redisServer.stop();
	}
	
	
	@Test
	public void getTest() {
		Beer[] beers = restTemplate.getForObject("/beer", Beer[].class);
		Assert.assertNotNull(beers);
	}

//	@Test
	public void postTest() {

		Beer beer = new Beer();
		beer.setName("Test" + RandomStringUtils.randomAlphanumeric(6));
		beer.setDescription("Test description");
		
		Beer savedBeer = restTemplate.postForObject("/beer", beer, Beer.class);
		
		Assert.assertNotNull(savedBeer);
		Assert.assertNotNull(savedBeer.getId());
	}

}
