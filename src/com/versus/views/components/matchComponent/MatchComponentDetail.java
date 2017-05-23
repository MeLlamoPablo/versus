package com.versus.views.components.matchComponent;

import com.versus.model.Competitor;
import com.versus.model.Match;
import com.versus.model.MatchResult;

import javax.swing.*;
import java.awt.*;

class MatchComponentDetail extends JPanel {

	private static Competitor competitorFallback = new Competitor("Por decidir");

	private JLabel localScore;
	private JLabel visitorScore;

	MatchComponentDetail(Match match) {

		super(new GridLayout(2, 2));

		JLabel localName = new JLabel(match.getLocalCompetitor().orElse(competitorFallback).getName()+"     ");
		JLabel visitorName = new JLabel(match.getVisitorCompetitor().orElse(competitorFallback).getName()+"     ");

		if (match.getResult().isPresent()) {

			MatchResult result = match.getResult().get();

			localScore = new JLabel(String.valueOf(result.getLocalScore()));
			visitorScore = new JLabel(String.valueOf(result.getVisitorScore()));

			this.add(localName);
			this.add(localScore);

			this.add(visitorName);
			this.add(visitorScore);


		} else {

			localScore = new JLabel("-");
			visitorScore = new JLabel("-");

			this.add(localName);
			this.add(localScore);

			this.add(visitorName);
			this.add(visitorScore);

		}

		this.setVisible(true);

	}

	void setResult(MatchResult result) {

		localScore.setText(String.valueOf(result.getLocalScore()));
		visitorScore.setText(String.valueOf(result.getVisitorScore()));

	}

}
