package com.versus.model;

public class RankedCompetitor extends Competitor {

	private int points;
	private int wins;
	private int draws;
	private int loses;
	private int scored;
	private int scoredAgainst;

	public int getPoints() {
		return points;
	}

	void setPoints(int points) {
		this.points = points;
	}

	public int getWins() {
		return wins;
	}

	void setWins(int wins) {
		this.wins = wins;
	}

	public int getDraws() {
		return draws;
	}

	void setDraws(int draws) {
		this.draws = draws;
	}

	public int getLoses() {
		return loses;
	}

	void setLoses(int loses) {
		this.loses = loses;
	}

	public int getScored() {
		return scored;
	}

	void setScored(int scored) {
		this.scored = scored;
	}

	public int getScoredAgainst() {
		return scoredAgainst;
	}

	void setScoredAgainst(int scoredAgainst) {
		this.scoredAgainst = scoredAgainst;
	}

	RankedCompetitor(Competitor competitor) {
		super(competitor.getName());

		this.setId(competitor.getId());
	}
}
