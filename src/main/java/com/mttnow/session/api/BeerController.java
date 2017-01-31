package com.mttnow.session.api;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mttnow.session.mongo.model.Beer;
import com.mttnow.session.mongo.repository.BeerRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BeerController {

	private static final Logger LOG = LoggerFactory.getLogger(BeerController.class);

	@Autowired
	private BeerRepository beerRepository;

	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(path = "/flush-cache", method = RequestMethod.POST)
	public void flushCache() {

		Collection<String> cacheNames = cacheManager.getCacheNames();
		for (String cacheName : cacheNames) {
			Cache cache = cacheManager.getCache(cacheName);
			cache.clear();
		}

	}

	@Cacheable(cacheNames = "beerResult", unless = "#result != null and #result.size() == 0")
	@ApiOperation(value = "getBeers", nickname = "getBeers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Beer[].class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(path = "/beer", method = RequestMethod.GET)
	public List<Beer> findAll() {
		LOG.info("Retrieving beers from Database");
		List<Beer> allBeers = beerRepository.findAll();
		return allBeers;
	}

	@CacheEvict(cacheNames = "beerResult", allEntries = true)
	@RequestMapping(path = "/beer", method = RequestMethod.POST)
	public Beer save(@RequestBody Beer beer) {
		beer = beerRepository.save(beer);
		return beer;
	}

	@RequestMapping(path = "/beer/{id}", method = RequestMethod.GET)
	public Beer findOne(@PathVariable("id") String id) {
		Beer beer = beerRepository.findOne(id);
		return beer;
	}

	@CacheEvict(allEntries = true)
	@RequestMapping(path = "/beer/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		beerRepository.deleteById(id);
	}

}
