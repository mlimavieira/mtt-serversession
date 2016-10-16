package com.mttnow.session.mongo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

	private String id;
	private String customer;

	@DBRef(lazy=true)
	private List<Beer> beers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<Beer> getBeers() {
		if(beers == null) {
			beers = new ArrayList<>();
		}
		return beers;
	}

	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}
}
