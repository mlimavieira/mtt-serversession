package com.mttnow.session;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mttnow.session.mongo.model.Beer;
import com.mttnow.session.mongo.model.Order;
import com.mttnow.session.mongo.repository.BeerRepository;
import com.mttnow.session.mongo.repository.OrderRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderRepositoryTests {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BeerRepository beerRepository;
	
	
//	@Test
//	public void saveTest() {
//		
//		Beer beer = null;
//		
//		List<Beer> allBeers = beerRepository.findAll();
//		if(allBeers == null || allBeers.isEmpty()) {
//			beer = new Beer();
//			beer.setName("Guinnes");
//			beer = beerRepository.save(beer);
//		} else {
//			beer = allBeers.get(0);
//		}
//		
//		Order order = new Order();
//		order.setCustomer("Marcio");
//		order.getBeers().add(beer);
//		
//		orderRepository.save(order);	
//	}
//	
	
	@Test
	public void test() {
		Order order = orderRepository.findOne("57fbb5e346534a06320baa1f");
		System.out.println("-");
	
		for (Beer b : order.getBeers()) {
			b.getName();
		}
		
	}
	
	
}
