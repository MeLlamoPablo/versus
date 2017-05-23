package com.versus.views.leagueCompetitionView;

import com.versus.model.LeagueCompetition;
import com.versus.views.support.*;
import com.versus.views.support.MenuElement;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

class LeagueCompetitionPanel extends JPanel {

	private LeagueCompetition competition;

	LeagueCompetition getCompetition() {
		return competition;
	}

	void setCompetition(LeagueCompetition competition) {
		this.competition = competition;
	}

	private List<com.versus.views.support.MenuElement> getMenuElements() {

		List<MenuElement> elements = new ArrayList<>();

		elements.add(new MenuElement("Clasificación", new LeagueCompetitionViewStandings(competition)));
		elements.add(new MenuElement("Partidos", new LeagueCompetitionViewMatches(competition)));

		return elements;

	}

	LeagueCompetitionPanel(LeagueCompetition competition) {
		super();
		this.setCompetition(competition);

		this.setLayout(null);

		this.add(new MenuUI(LeagueCompetitionView.WIDTH, LeagueCompetitionView.HEIGHT, getMenuElements()));

		this.setVisible(true);
	}

}
