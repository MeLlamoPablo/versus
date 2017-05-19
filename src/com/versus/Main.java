package com.versus;

import com.versus.model.*;

public class Main {

    public static void main(String[] args) {

		try {

			/*SingleEliminationCompetition clasificatoria = new SingleEliminationCompetition(
				"Clasificatoria");

			SingleEliminationCompetition principal = new SingleEliminationCompetition(
				"Principal");

			clasificatoria.setLink(new CompetitionLink(principal, 1));

			clasificatoria.addCompetitor(new Competitor("Competitor 1"));
			clasificatoria.addCompetitor(new Competitor("Competitor 2"));
			clasificatoria.addCompetitor(new Competitor("Competitor 3"));
			clasificatoria.addCompetitor(new Competitor("Competitor 4"));
			clasificatoria.addCompetitor(new Competitor("Competitor 5"));
			clasificatoria.addCompetitor(new Competitor("Competitor 6"));
			clasificatoria.addCompetitor(new Competitor("Competitor 7"));
			clasificatoria.addCompetitor(new Competitor("Competitor 8"));

			clasificatoria.shuffleSeeds();

			clasificatoria.setCompetitionEndedListener(winner -> {
				System.out.println("La competición " + clasificatoria.getName() + " ha terminado!");
				System.out.println("El ganador es: " + winner.getName());
				System.out.println("El ganador se ha clasificado para: " + principal.getName());
			});

			Bracket bracket = clasificatoria.generateBracket();

			// Cuartos
			bracket.getRound(0).getMatch(0).setResult(2, 0);
			bracket.getRound(0).getMatch(1).setResult(1, 2);
			bracket.getRound(0).getMatch(2).setResult(2, 1);
			bracket.getRound(0).getMatch(3).setResult(3, 1);

			// Semifinales
			bracket.getRound(1).getMatch(0).setResult(3, 0);
			bracket.getRound(1).getMatch(1).setResult(1, 2);

			// Final
			bracket.getRound(2).getMatch(0).setResult(0, 1);*/

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

		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}
