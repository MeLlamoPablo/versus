package com.versus;

import com.versus.model.Bracket;
import com.versus.model.Competitor;
import com.versus.model.MatchResult;
import com.versus.model.SingleEliminationCompetition;

public class Main {

    public static void main(String[] args) {

		SingleEliminationCompetition competition = new SingleEliminationCompetition(
			"Torneo de prueba");

		competition.addCompetitor(new Competitor("Competitor 1"));
		competition.addCompetitor(new Competitor("Competitor 2"));
		competition.addCompetitor(new Competitor("Competitor 3"));
		competition.addCompetitor(new Competitor("Competitor 4"));
		competition.addCompetitor(new Competitor("Competitor 5"));
		competition.addCompetitor(new Competitor("Competitor 6"));
		competition.addCompetitor(new Competitor("Competitor 7"));
		competition.addCompetitor(new Competitor("Competitor 8"));

		Bracket bracket = competition.generateBracket();

		MatchResult result1 = new MatchResult(2, 0);
		competition.getBracket().getRound(0).getMatch(0).setResult(result1);
		competition.getBracket().getRound(0).updateLinks();

		System.out.println("holas");

    }
}
