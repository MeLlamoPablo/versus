package com.versus.model;

import java.util.UUID;

abstract class Entity {

	private String id = UUID.randomUUID().toString();

	public String getId() {
		return this.id;
	}

	void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;

		Entity entity = (Entity) o;

		return this.getId().equals(entity.getId());
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
