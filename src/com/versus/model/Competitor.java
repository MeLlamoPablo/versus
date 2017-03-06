package com.versus.model;

public class Competitor {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public Competitor setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Competitor setName(String name) {
		this.name = name;
		return this;
	}

	public Competitor(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
}
