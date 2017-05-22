package com.versus.views;

import com.versus.model.LeagueCompetition;
import com.versus.views.support.JFrameX;


public class LeagueCompetitionView extends JFrameX {

	static int WIDTH = 1000;
	static int HEIGHT = 700;

	private LeagueCompetition competition;

	LeagueCompetition getCompetition() {
		return competition;
	}

	void setCompetition(LeagueCompetition competition) {
		this.competition = competition;
	}

	public LeagueCompetitionView(LeagueCompetition competition) {
		super(competition.getName(), WIDTH, HEIGHT);
		this.setCompetition(competition);

		this.add(new LeagueCompetitionPanel(competition));

		this.setVisible(true);
	}

}

