package com.versus.views;

import com.versus.model.LeagueCompetition;

import javax.swing.*;
import java.awt.*;
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

	private List<MenuElement> getMenuElements() {


		List<MenuElement> elements = new ArrayList<>();

		elements.add(new MenuElement("Clasificación", new LeagueCompetitionViewStandings()));

		JPanel content = new JPanel();

		content.setLayout(new FlowLayout());
		content.add(new JLabel("MENU 2"));

		elements.add(new MenuElement("Menú 2", content));

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
