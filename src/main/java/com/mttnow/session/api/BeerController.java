package com.mttnow.session.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mttnow.session.mongo.model.Beer;
import com.mttnow.session.mongo.repository.BeerRepository;

@RestController
public class BeerController {

	@Autowired
	private BeerRepository beerRepository;

	@RequestMapping(path = "/beer/list", method = RequestMethod.GET)
	public List<Beer> findAll() {
		List<Beer> allBeers = beerRepository.findAll();
		return allBeers;
	}

	@RequestMapping(path = "/beer/{id}", method = RequestMethod.GET)
	public Beer findOne(@PathVariable("id") String id) {
		return beerRepository.findOne(id);
	}

	@RequestMapping(path = "/beer/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		beerRepository.deleteById(id);
	}

	@RequestMapping(path = "/beer", method = RequestMethod.POST)
	public Beer save(@RequestBody Beer beer) {
		beer = beerRepository.save(beer);
		return beer;
	}
}


