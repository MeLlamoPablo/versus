package com.versus.model;

@SuppressWarnings("SameParameterValue")
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

	void addPoints(int points) {
		this.setPoints(this.getPoints() + points);
	}

	public int getWins() {
		return wins;
	}

	void setWins(int wins) {
		this.wins = wins;
	}

	void addWins(int wins) {
		this.setWins(this.getWins() + wins);
	}

	public int getDraws() {
		return draws;
	}

	void setDraws(int draws) {
		this.draws = draws;
	}

	void addDraws(int draws) {
		this.setDraws(this.getDraws() + draws);
	}

	public int getLoses() {
		return loses;
	}

	void setLoses(int loses) {
		this.loses = loses;
	}

	void addLoses(int loses) {
		this.setLoses(this.getLoses() + loses);
	}

	public int getScored() {
		return scored;
	}

	void setScored(int scored) {
		this.scored = scored;
	}

	void addScored(int scored) {
		this.setScored(this.getScored() + scored);
	}

	public int getScoredAgainst() {
		return scoredAgainst;
	}

	void setScoredAgainst(int scoredAgainst) {
		this.scoredAgainst = scoredAgainst;
	}

	void addScoredAgainst(int scoredAgainst) {
		this.setScoredAgainst(this.getScoredAgainst() + scoredAgainst);
	}

	RankedCompetitor(Competitor competitor) {
		super(competitor.getName());

		this.setId(competitor.getId());
	}

	@Override
	public String toString() {
		return String.format(
			"%s (PTS: %d, W: %d, D: %d, L: %d, S: %d, SA: %d)",
			this.getName(),
			this.getPoints(),
			this.getWins(),
			this.getDraws(),
			this.getLoses(),
			this.getScored(),
			this.getScoredAgainst()
		);
	}

}
