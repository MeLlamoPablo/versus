package com.versus.model;

import com.versus.model.interfaces.BracketEndedListener;
import com.versus.model.interfaces.EliminationCompetitionEndedListener;

import java.util.Optional;

abstract class EliminationCompetition extends Competition implements BracketEndedListener {

	private Competitor winner;
	private EliminationCompetitionEndedListener competitionEndedListener;

	public Optional<Competitor> getWinner() {
		return Optional.ofNullable(winner);
	}

	public void setWinner(Competitor winner) {
		this.winner = winner;
	}

	protected Optional<EliminationCompetitionEndedListener> getCompetitionEndedListener() {
		return Optional.ofNullable(competitionEndedListener);
	}

	public void setCompetitionEndedListener(EliminationCompetitionEndedListener competitionEndedListener) {
		this.competitionEndedListener = competitionEndedListener;
	}

	public EliminationCompetition(String name) {
		super(name);
	}

	@Override
	// No permitimos empates en las competiciones eliminatorias
	boolean isResultValid(MatchResult result) {
		return result.get() != MatchResult.EMatchWinner.DRAW;
	}

	@Override
	public void shuffleSeeds() {
		super.shuffleSeeds();
	}

	@Override
	public void sendCompetitorsToNextCompetition() {

		this.getLink().ifPresent(link -> {

			assert link.getSpots() == 1;
			assert this.getWinner().isPresent();

			link.getTarget().addCompetitor(this.getWinner().get());

		});

	}
}
