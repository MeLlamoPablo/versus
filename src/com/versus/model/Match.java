package com.versus.model;

import com.versus.model.exceptions.BadInputException;
import com.versus.model.exceptions.NullInputException;
import com.versus.model.interfaces.MatchUpdatedListener;

import java.util.Optional;

public class Match extends Entity {

	private MatchResult result;
	private Competitor localCompetitor;
	private Competitor visitorCompetitor;
	private Competition competition;
	private MatchLink link = new MatchLink();
	private MatchUpdatedListener matchUpdatedListener;

	public Optional<MatchResult> getResult() {
		return Optional.ofNullable(this.result);
	}

	public void setResult(int localScore, int visitorScore) throws BadInputException {
		this.setResult(new MatchResult(localScore, visitorScore));
	}

	private void setResult(MatchResult result) throws BadInputException {

		if (!this.getCompetition().isResultValid(result)) {
			throw new BadInputException("The result you entered is invalid.");
		}

		if (!this.getLocalCompetitor().isPresent() || !this.getVisitorCompetitor().isPresent()) {
			throw new BadInputException("Can't set a result for this match because one of the competitors is missing!");
		}

		this.result = result;

		this.getMatchUpdatedListener().ifPresent(listener -> listener.onMatchUpdated(this));

	}

	public Optional<Competitor> getLocalCompetitor() {
		return Optional.ofNullable(localCompetitor);
	}

	public void setLocalCompetitor(Competitor localCompetitor) {
		this.localCompetitor = localCompetitor;
	}

	public Optional<Competitor> getVisitorCompetitor() {
		return Optional.ofNullable(visitorCompetitor);
	}

	public void setVisitorCompetitor(Competitor visitorCompetitor) {
		this.visitorCompetitor = visitorCompetitor;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		if (competition == null) {
			throw new NullInputException("Competition can't be null!");
		}

		this.competition = competition;
	}

	public MatchLink getLink() {
		return link;
	}

	private Optional<MatchUpdatedListener> getMatchUpdatedListener() {
		return Optional.ofNullable(matchUpdatedListener);
	}

	public void setMatchUpdatedListener(MatchUpdatedListener matchUpdatedListener) {
		this.matchUpdatedListener = matchUpdatedListener;
	}

	public Optional<Competitor> getWinner() {

		Optional<MatchResult> result = this.getResult();

		if (!result.isPresent()) {
			return Optional.empty();
		}

		switch (result.get().get()) {

			case LOCAL: return this.getLocalCompetitor();
			case VISITOR: return this.getVisitorCompetitor();
			case DRAW: return Optional.empty();
			default: return Optional.empty();

		}

	}

	public Optional<Competitor> getLoser() {

		Optional<MatchResult> result = this.getResult();

		if (!result.isPresent()) {
			return Optional.empty();
		}

		switch (result.get().get()) {

			case VISITOR: return this.getLocalCompetitor();
			case LOCAL: return this.getVisitorCompetitor();
			case DRAW: return Optional.empty();
			default: return Optional.empty();

		}

	}

	@Override
	public String toString() {

		Optional<MatchResult> resultOpt = this.getResult();

		Competitor tbd = new Competitor("TBD");

		if (!resultOpt.isPresent()) {

			return this.getLocalCompetitor().orElse(tbd).getName() + " - "
				+ this.getVisitorCompetitor().orElse(tbd).getName();

		} else {

			MatchResult result = resultOpt.get();

			return this.getLocalCompetitor().orElse(tbd).getName() + " " + result.getLocalScore() +
				" - " + result.getVisitorScore() + " " + this.getVisitorCompetitor().orElse(tbd).getName();

		}

	}
}
