package com.versus.model;

import com.versus.model.interfaces.BracketEndedListener;

abstract class EliminationCompetition extends Competition implements BracketEndedListener {

	private Competitor winner;

	public Competitor getWinner() {
		return winner;
	}

	public void setWinner(Competitor winner) {
		this.winner = winner;
	}

	public EliminationCompetition(String name) {
		super(name);
	}

	@Override
	// No permitimos empates en las competiciones eliminatorias
	boolean isResultValid(MatchResult result) {
		return result.getWinner() != MatchResult.EMatchWinner.DRAW;
	}

	@Override
	public void shuffleSeeds() {
		super.shuffleSeeds();
	}

	@Override
	public void sendCompetitorsToNextCompetition() {

		CompetitionLink link = this.getLink();

		if (link != null) {

			assert link.getSpots() == 1;

			link.getTarget().addCompetitor(this.getWinner());

		}


	}
}
