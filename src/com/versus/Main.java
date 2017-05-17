package com.versus;

import com.versus.model.Bracket;
import com.versus.model.Competitor;
import com.versus.model.MatchResult;
import com.versus.model.SingleEliminationCompetition;
import com.versus.model.interfaces.CompetitionEndedListener;

public class Main {

    public static void main(String[] args) {

		try {

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

			competition.shuffleSeeds();

			competition.setCompetitionEndedListener(winner -> {
				System.out.println("La competición " + competition.getName() + " ha terminado!");
				System.out.println("El ganador es: " + winner.getName());
			});

			Bracket bracket = competition.generateBracket();

			// Cuartos
			bracket.getRound(0).getMatch(0).setResult(2, 0);
			bracket.getRound(0).getMatch(1).setResult(1, 2);
			bracket.getRound(0).getMatch(2).setResult(2, 1);
			bracket.getRound(0).getMatch(3).setResult(3, 1);

			// Semifinales
			bracket.getRound(1).getMatch(0).setResult(3, 0);
			bracket.getRound(1).getMatch(1).setResult(1, 2);

			// Final
			bracket.getRound(2).getMatch(0).setResult(0, 1);

		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}
