package com.versus;

import com.versus.model.Competitor;
import com.versus.model.SingleEliminationCompetition;

public class Main {

    public static void main(String[] args) {

		SingleEliminationCompetition competition = new SingleEliminationCompetition(
			1,
			"Torneo de prueba");

		Competitor[] competitors = new Competitor[3];

		competitors[0] = new Competitor(1, "Competitor 1");
		competitors[1] = new Competitor(2, "Competitor 2");
		competitors[2] = new Competitor(3, "Competitor 3");

		competition.setCompetitors(competitors);

		System.out.println("");

    }
}
