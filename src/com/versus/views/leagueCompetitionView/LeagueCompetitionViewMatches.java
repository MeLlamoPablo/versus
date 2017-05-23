package com.versus.views.leagueCompetitionView;

import com.versus.model.LeagueCompetition;
import com.versus.model.interfaces.MatchUpdatedListener;
import com.versus.views.components.roundComponent.RoundComponent;

import javax.swing.*;
import java.awt.*;

class LeagueCompetitionViewMatches extends JPanel {

	private LeagueCompetition competition;

	LeagueCompetition getCompetition() {
		return competition;
	}

	void setCompetition(LeagueCompetition competition) {
		this.competition = competition;
	}

	LeagueCompetitionViewMatches(LeagueCompetition competition, MatchUpdatedListener callback) {

		this.setCompetition(competition);

		this.setLayout(new FlowLayout());

		for (int i = 0; i < competition.getRounds().size(); i++) {
			this.add(new RoundComponent(competition.getRound(i), "Jornada " + (i + 1), callback));
		}

		this.setVisible(true);

	}
}
