package com.versus.model;

import java.util.ArrayList;
import java.util.List;

abstract class Competition extends Entity {

	private String name;
	private List<Competitor> competitors = new ArrayList<>();
	private CompetitionLink link;

	public Competition setId() {
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCompetitor(Competitor competitor) {
		this.competitors.add(competitor);
	}

	public List<Competitor> getCompetitors() {
		return competitors;
	}

	public CompetitionLink getLink() {
		return link;
	}

	public void setLink(CompetitionLink link) {
		this.link = link;
	}

	public Competition(String name) {
		this.setName(name);
	}
}
