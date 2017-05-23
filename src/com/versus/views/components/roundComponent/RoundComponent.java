package com.versus.views.components.roundComponent;

import com.versus.model.Round;
import com.versus.model.interfaces.MatchUpdatedListener;
import com.versus.views.components.matchComponent.MatchComponent;

import javax.swing.*;

public class RoundComponent extends JPanel {

	public RoundComponent(Round round, String name, MatchUpdatedListener callback) {

		super();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(new JLabel(name));

		round
			.getMatches()
			.stream()
			.map(match -> new MatchComponent(match, callback))
			.forEach(this::add);

	}
}
