package com.versus;

import com.versus.model.Competitor;
import com.versus.model.LeagueCompetition;
import com.versus.model.exceptions.BadInputException;
import com.versus.views.leagueCompetitionView.LeagueCompetitionView;

public class Main {

	public static void main(String[] args) throws BadInputException {

		LeagueCompetition league = new LeagueCompetition("Liga de prueba");

		league.addCompetitor(new Competitor("Madrid"));
		league.addCompetitor(new Competitor("Barça"));
		league.addCompetitor(new Competitor("Atleti"));
		league.addCompetitor(new Competitor("Sevilla"));

		league.generateRounds();

		league.getRound(0).getMatch(0).setResult(2, 0);
		league.getRound(0).getMatch(1).setResult(1, 1);

		new LeagueCompetitionView(league);

    }
}
