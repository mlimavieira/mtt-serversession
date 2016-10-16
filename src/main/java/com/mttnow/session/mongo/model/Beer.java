package com.mttnow.session.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
// @CompoundIndexes({
// @CompoundIndex(name = "idx_name_desc", def = "{'name': 1, 'descri': 1}",
// unique=true)
// })
public class Beer {

	@Id
	private String id;

	// @Indexed(unique = true)
	private String name;
	private String description;

	public Beer() {
		
		System.out.println("TESTE" );
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
