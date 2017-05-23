package com.versus.views.leagueCompetitionView;

import com.versus.model.LeagueCompetition;
import com.versus.model.Match;
import com.versus.model.interfaces.MatchUpdatedListener;
import com.versus.views.support.MenuUI;
import com.versus.views.support.MenuElement;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

class LeagueCompetitionPanel extends JPanel implements MatchUpdatedListener {

	private MenuElement standings;
	private MenuElement matches;

	private LeagueCompetition competition;

	LeagueCompetition getCompetition() {
		return competition;
	}

	void setCompetition(LeagueCompetition competition) {
		this.competition = competition;
	}

	private List<MenuElement> getMenuElements() {

		List<MenuElement> elements = new ArrayList<>();

		elements.add(standings);
		elements.add(matches);

		return elements;

	}

	LeagueCompetitionPanel(LeagueCompetition competition) {
		super();
		this.setCompetition(competition);

		this.standings = new MenuElement("Clasificación", new LeagueCompetitionViewStandings(competition));
		this.matches = new MenuElement("Partidos", new LeagueCompetitionViewMatches(competition, this));

		this.setLayout(null);

		this.add(new MenuUI(LeagueCompetitionView.WIDTH, LeagueCompetitionView.HEIGHT, getMenuElements()));

		this.setVisible(true);
	}

	@Override
	public void onMatchUpdated(Match match) {
		((LeagueCompetitionViewStandings) standings.getContent()).refreshTableData();
	}
}
