package com.mttnow.session;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mttnow.session.mongo.model.Beer;
import com.mttnow.session.mongo.repository.BeerRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BeerRepositoryTests {

	@Autowired
	private BeerRepository beerRepository;
	
	
	@Test
	public void saveTest() {
		Beer beer = new Beer();
		beer.setName("Guinnes");
		beer.setDescription("A Guinness é uma cerveja irlandesa cuja história teve início em 1759.");
		Beer savedBeer = beerRepository.save(beer);
		
		Assert.assertNotNull(savedBeer);
		Assert.assertNotNull(savedBeer.getId());
	}
	
}
