package com.versus.model;

public class LeagueCompetition extends Competition {

	public LeagueCompetition(String name) {
		super(name);
	}

	@Override
	// En una liga todos los resultados son válidos, incluidos los empates
	boolean isResultValid(MatchResult result) {
		return true;
	}

	@Override
	void sendCompetitorsToNextCompetition() {
		// TODO
	}
}
