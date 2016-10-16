package com.mttnow.session.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mttnow.session.mongo.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
