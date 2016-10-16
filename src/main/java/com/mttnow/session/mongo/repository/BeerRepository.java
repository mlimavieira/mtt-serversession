package com.mttnow.session.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mttnow.session.mongo.model.Beer;

public interface BeerRepository extends MongoRepository<Beer, String> {

	void deleteById(String id);
}
