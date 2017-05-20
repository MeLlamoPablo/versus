package com.versus.model;

import com.versus.model.interfaces.MatchUpdatedListener;

public class Match extends Entity {

	private MatchResult result;
	private Competitor localCompetitor;
	private Competitor visitorCompetitor;
	private Competition competition;
	private MatchLink link = new MatchLink();
	private MatchUpdatedListener matchUpdatedListener;

	public MatchResult getResult() {
		return result;
	}

	public void setResult(int localScore, int visitorScore) throws Exception {
		this.setResult(new MatchResult(localScore, visitorScore));
	}

	private void setResult(MatchResult result) throws Exception {

		if (!this.getCompetition().isResultValid(result)) {
			throw new Exception("The result you entered is invalid.");
		}

		if (this.getLocalCompetitor() == null || this.getVisitorCompetitor() == null) {
			throw new Exception("Can't set a result for this match because one of the competitors is missing!");
		}

		this.result = result;

		if (this.getMatchUpdatedListener() != null) {
			this.getMatchUpdatedListener().onMatchUpdated(this);
		}

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

	private MatchUpdatedListener getMatchUpdatedListener() {
		return matchUpdatedListener;
	}

	public void setMatchUpdatedListener(MatchUpdatedListener matchUpdatedListener) {
		this.matchUpdatedListener = matchUpdatedListener;
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

	@Override
	public String toString() {

		if (this.getResult() == null) {

			return this.getLocalCompetitor().getName() + " - " + this.getVisitorCompetitor().getName();

		} else {

			return this.getLocalCompetitor().getName() + " " + this.getResult().getLocalScore() +
				" - " + this.getResult().getVisitorScore() + " " + this.getVisitorCompetitor().getName();

		}

	}
}
