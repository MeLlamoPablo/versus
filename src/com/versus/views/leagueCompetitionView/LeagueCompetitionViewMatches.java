package com.versus.views.leagueCompetitionView;

import com.versus.model.LeagueCompetition;

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

	LeagueCompetitionViewMatches(LeagueCompetition competition) {

		this.setCompetition(competition);

		this.setLayout(new FlowLayout());

		this.add(new JLabel("Hola mundo")); // TODO
		this.setVisible(true);

	}
}
