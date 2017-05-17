package com.versus.model;

import com.versus.model.interfaces.BracketEndedListener;

abstract class EliminationCompetition extends Competition implements BracketEndedListener {

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
}
