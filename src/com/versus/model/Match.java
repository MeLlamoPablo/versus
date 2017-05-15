package com.versus.model;

public class Match extends Entity {

	private MatchResult result;
	private Competitor localCompetitor;
	private Competitor visitorCompetitor;
	private Competition competition;
	private MatchLink link = new MatchLink();

	public MatchResult getResult() {
		return result;
	}

	public void setResult(MatchResult result) {
		this.result = result;
	}

	public Competitor getLocalCompetitor() {
		return localCompetitor;
	}

	public void setLocalCompetitor(Competitor localCompetitor) {
		this.localCompetitor = localCompetitor;
	}

	public Competitor getVisitorCompetitor() {
		return visitorCompetitor;
	}

	public void setVisitorCompetitor(Competitor visitorCompetitor) {
		this.visitorCompetitor = visitorCompetitor;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public MatchLink getLink() {
		return link;
	}

	public Competitor getWinner() {

		if (this.getResult() == null) {
			return null;
		}

		switch (this.getResult().getWinner()) {

			case LOCAL: return this.getLocalCompetitor();
			case VISITOR: return this.getVisitorCompetitor();
			case DRAW: return null;
			default: return null;

		}

	}

	public Competitor getLoser() {

		if (this.getResult() == null) {
			return null;
		}

		switch (this.getResult().getWinner()) {

			case VISITOR: return this.getLocalCompetitor();
			case LOCAL: return this.getVisitorCompetitor();
			case DRAW: return null;
			default: return null;

		}

	}

}
