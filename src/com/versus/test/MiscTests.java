package com.versus.test;

import com.versus.model.Bracket;
import com.versus.model.Competitor;
import com.versus.model.LeagueCompetition;
import com.versus.model.SingleEliminationCompetition;
import com.versus.model.exceptions.BadInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiscTests {

	@Test
	void itShouldLinkCompetitions() throws BadInputException {

		LeagueCompetition qualifier = new LeagueCompetition("Clasificatoria");

		qualifier.addCompetitor(LeagueCompetitionTest.madrid);
		qualifier.addCompetitor(LeagueCompetitionTest.barsa);
		qualifier.addCompetitor(LeagueCompetitionTest.atleti);
		qualifier.addCompetitor(LeagueCompetitionTest.sevilla);

		SingleEliminationCompetition playoffs = new SingleEliminationCompetition("Playoffs");

		playoffs.addCompetitor(new Competitor("Competitor 1"));
		playoffs.addCompetitor(new Competitor("Competitor 2"));

		qualifier.setLink(playoffs, 2);

		qualifier.setCompetitionEndedListener(() -> {

			assertEquals(4, playoffs.getCompetitors().size());

			assert playoffs.getCompetitors().contains(LeagueCompetitionTest.sevilla);

			try {

				Bracket bracket = playoffs.generateBracket();

				// Semifinales
				bracket.getRound(0).getMatch(0).setResult(2, 4); // 1 vs 2
				bracket.getRound(0).getMatch(1).setResult(3, 2); // Sevilla vs ?

				// Final
				bracket.getRound(1).getMatch(0).setResult(0, 1); // 2 vs Sevilla

				assert playoffs.getWinner().isPresent();
				assertEquals("Sevilla", playoffs.getWinner().get().getName());

			} catch (BadInputException e) {
				fail(e);
			}

		});

		qualifier.generateRounds();

		LeagueCompetitionTest.processMatch(qualifier.getRound(0).getMatch(0));
		LeagueCompetitionTest.processMatch(qualifier.getRound(0).getMatch(1));

		LeagueCompetitionTest.processMatch(qualifier.getRound(1).getMatch(0));
		LeagueCompetitionTest.processMatch(qualifier.getRound(1).getMatch(1));

		LeagueCompetitionTest.processMatch(qualifier.getRound(2).getMatch(0));
		LeagueCompetitionTest.processMatch(qualifier.getRound(2).getMatch(1));

	}

}
