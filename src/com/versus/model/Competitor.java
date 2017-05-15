package com.versus.model;

public class Competitor extends Entity {

	private String name;

	public Competitor setId() {
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Competitor(String name) {
		this.setName(name);
	}
}
