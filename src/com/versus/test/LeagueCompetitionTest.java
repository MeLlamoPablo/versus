package com.versus.test;

import com.versus.model.Competitor;
import com.versus.model.LeagueCompetition;
import com.versus.model.Match;
import com.versus.model.RankedCompetitor;
import com.versus.model.exceptions.BadInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class LeagueCompetitionTest {

	static Competitor madrid = new Competitor("Madrid");
	static Competitor barsa = new Competitor("Barça");
	static Competitor atleti = new Competitor("Atleti");
	static Competitor sevilla = new Competitor("Sevilla");

	@Test
	void itShouldCreateASimpleLeague() throws BadInputException {

		LeagueCompetition league = new LeagueCompetition("Liga");

		league.addCompetitor(madrid);
		league.addCompetitor(barsa);
		league.addCompetitor(atleti);
		league.addCompetitor(sevilla);

		league.setCompetitionEndedListener(() -> {

			List<RankedCompetitor> ranking = league.getRanking();

			assertEquals("Sevilla", ranking.get(0).getName());

			String second = ranking.get(1).getName();
			String third = ranking.get(2).getName();

			assertEquals(second.equals("Barça") || second.equals("Atleti"), true);
			assertEquals(third.equals("Barça") || third.equals("Atleti"), true);

			assertEquals("Madrid", ranking.get(3).getName());

		});

		league.generateRounds();

		processMatch(league.getRound(0).getMatch(0));
		processMatch(league.getRound(0).getMatch(1));

		processMatch(league.getRound(1).getMatch(0));
		processMatch(league.getRound(1).getMatch(1));

		processMatch(league.getRound(2).getMatch(0));
		processMatch(league.getRound(2).getMatch(1));

	}

	/**
	 * Hacemos que el Sevilla siempre gane, el Madrid siempre pierda, y el resto empaten entre sí.
	 */
	static void processMatch(Match match) throws BadInputException {

		boolean madridParticipates = madridParticipates(match);
		boolean sevillaParticipates = sevillaParticipates(match);

		if (madridParticipates || sevillaParticipates) {

			if (sevillaParticipates) {

				if (sevilla.equals(match.getLocalCompetitor().orElse(null))) {

					match.setResult(3, 0);

				} else {

					match.setResult(1, 3);

				}

			} else {

				if (madrid.equals(match.getLocalCompetitor().orElse(null))) {

					match.setResult(0, 2);

				} else {

					match.setResult(2, 1);

				}

			}

		} else {

			match.setResult(1, 1);

		}

	}

	private static boolean sevillaParticipates(Match match) {
		return
			sevilla.equals(match.getLocalCompetitor().orElse(null)) ||
			sevilla.equals(match.getVisitorCompetitor().orElse(null));
	}

	private static boolean madridParticipates(Match match) {
		return
			madrid.equals(match.getLocalCompetitor().orElse(null)) ||
			madrid.equals(match.getVisitorCompetitor().orElse(null));
	}

}
