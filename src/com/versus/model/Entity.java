package com.versus.model;

import java.util.UUID;

abstract class Entity {

	private String id = UUID.randomUUID().toString();

	public String getId() {
		return this.id;
	}

}
