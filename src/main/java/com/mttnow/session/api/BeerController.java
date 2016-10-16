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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BeerController {

	@Autowired
	private BeerRepository beerRepository;

	@ApiOperation(value = "getBeers", nickname = "getBeers")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Beer[].class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
	@RequestMapping(path = "/beer", method = RequestMethod.GET)
	public List<Beer> findAll() {
		List<Beer> allBeers = beerRepository.findAll();
		return allBeers;
	}

	@RequestMapping(path = "/beer", method = RequestMethod.POST)
	public Beer save(@RequestBody Beer beer) {
		beer = beerRepository.save(beer);
		return beer;
	}

	@RequestMapping(path = "/beer/{id}", method = RequestMethod.GET)
	public Beer findOne(@PathVariable("id") String id) {
		return beerRepository.findOne(id);
	}

	@RequestMapping(path = "/beer/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		beerRepository.deleteById(id);
	}

}
