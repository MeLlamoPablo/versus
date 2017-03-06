package com.versus.model;

public class Match {

	private int id;
	private MatchResult result;
	private Competitor localCompetitor;
	private Competitor visitorCompetitor;
	private Competition competition;
	private String position;

	public int getId() {
		return id;
	}

	public Match setId(int id) {
		this.id = id;
		return this;
	}

	public MatchResult getResult() {
		return result;
	}

	public Match setResult(MatchResult result) {
		this.result = result;
		return this;
	}

	public Competitor getLocalCompetitor() {
		return localCompetitor;
	}

	public Match setLocalCompetitor(Competitor localCompetitor) {
		this.localCompetitor = localCompetitor;
		return this;
	}

	public Competitor getVisitorCompetitor() {
		return visitorCompetitor;
	}

	public Match setVisitorCompetitor(Competitor visitorCompetitor) {
		this.visitorCompetitor = visitorCompetitor;
		return this;
	}

	public Competition getCompetition() {
		return competition;
	}

	public Match setCompetition(Competition competition) {
		this.competition = competition;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public Match setPosition(String position) {
		this.position = position;
		return this;
	}

	public Match(int id) {
		this.setId(id);
	}
}
