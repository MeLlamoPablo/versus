package com.versus.test;

import com.versus.model.Bracket;
import com.versus.model.Competitor;
import com.versus.model.DoubleEliminationCompetition;
import com.versus.model.exceptions.BadInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleEliminationCompetitionTest {

	@Test
	void itShouldCreateASimpleCompetition() throws BadInputException {

		DoubleEliminationCompetition competition = new DoubleEliminationCompetition("Competition");

		competition.addCompetitor(new Competitor("Competitor 1"));
		competition.addCompetitor(new Competitor("Competitor 2"));
		competition.addCompetitor(new Competitor("Competitor 3"));
		competition.addCompetitor(new Competitor("Competitor 4"));
		competition.addCompetitor(new Competitor("Competitor 5"));
		competition.addCompetitor(new Competitor("Competitor 6"));
		competition.addCompetitor(new Competitor("Competitor 7"));
		competition.addCompetitor(new Competitor("Competitor 8"));

		competition.generateBrackets();

		competition.setCompetitionEndedListener(winner ->
			assertEquals("Competitor 7", winner.getName()));

		//// CUADRO SUPERIOR ////

		Bracket upperBracket = competition.getUpperBracket();

		// Cuartos
		upperBracket.getRound(0).getMatch(0).setResult(2, 0); // 1 vs 2
		upperBracket.getRound(0).getMatch(1).setResult(1, 2); // 3 vs 4
		upperBracket.getRound(0).getMatch(2).setResult(2, 1); // 5 vs 6
		upperBracket.getRound(0).getMatch(3).setResult(3, 1); // 7 vs 8

		// Semifinales
		upperBracket.getRound(1).getMatch(0).setResult(3, 0); // 1 vs 4
		upperBracket.getRound(1).getMatch(1).setResult(1, 2); // 5 vs 7

		// Final
		upperBracket.getRound(2).getMatch(0).setResult(0, 1); // 1 vs 7

		//// CUADRO INFERIOR ////

		Bracket lowerBracket = competition.getLowerBracket();

		// Ronda 1
		lowerBracket.getRound(0).getMatch(0).setResult(1, 0); // 2 vs 3
		lowerBracket.getRound(0).getMatch(1).setResult(3, 1); // 6 vs 8

		// Ronda 2
		lowerBracket.getRound(1).getMatch(0).setResult(1, 0); // 4 vs 2
		lowerBracket.getRound(1).getMatch(1).setResult(1, 2); // 5 vs 6

		// Ronda 3
		lowerBracket.getRound(2).getMatch(0).setResult(0, 2); // 4 vs 6

		// Ronda 4
		lowerBracket.getRound(3).getMatch(0).setResult(3, 2); // 1 vs 6

		///// GRAN FINAL /////

		competition.getFinals().setResult(3, 1); // 7 vs 1

	}

}
