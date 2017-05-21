package com.versus.test;

import static org.junit.jupiter.api.Assertions.*;

import com.versus.model.Bracket;
import com.versus.model.Competitor;
import com.versus.model.SingleEliminationCompetition;
import com.versus.model.exceptions.BadInputException;
import org.junit.jupiter.api.Test;

class SingleEliminationCompetitionTest {

	@Test
	void itShouldCreateASimpleCompetition() throws BadInputException {

		SingleEliminationCompetition competition = new SingleEliminationCompetition("Competition");

		assertEquals("Competition", competition.getName());

		competition.addCompetitor(new Competitor("Competitor 1"));
		competition.addCompetitor(new Competitor("Competitor 2"));
		competition.addCompetitor(new Competitor("Competitor 3"));
		competition.addCompetitor(new Competitor("Competitor 4"));

		competition.setCompetitionEndedListener(winner ->
			assertEquals("Competitor 4", winner.getName()));

		Bracket bracket = competition.generateBracket();

		// Semifinales
		bracket.getRound(0).getMatch(0).setResult(3, 0); // 1 vs 2
		bracket.getRound(0).getMatch(1).setResult(1, 2); // 3 vs 4

		// Final
		bracket.getRound(1).getMatch(0).setResult(0, 1); // 1 vs 4

	}

}
