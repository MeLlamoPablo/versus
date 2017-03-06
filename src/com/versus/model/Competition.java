package com.versus.model;

class Competition {

	private int id;
	private String name;
	private Competitor[] competitors;
	private Match[] matches;
	private Competition linksTo;
	private int linkSpots;

	public int getId() {
		return id;
	}

	public Competition setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Competition setName(String name) {
		this.name = name;
		return this;
	}

	public Competitor[] getCompetitors() {
		return competitors;
	}

	public Competition setCompetitors(Competitor[] competitors) {
		this.competitors = competitors;
		return this;
	}

	public Match[] getMatches() {
		return matches;
	}

	public Competition setMatches(Match[] matches) {
		this.matches = matches;
		return this;
	}

	public Competition getLinksTo() {
		return linksTo;
	}

	public Competition setLinksTo(Competition linksTo) {
		this.linksTo = linksTo;
		return this;
	}

	public int getLinkSpots() {
		return linkSpots;
	}

	public Competition setLinkSpots(int linkSpots) {
		this.linkSpots = linkSpots;
		return this;
	}

	public Competition(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
}
