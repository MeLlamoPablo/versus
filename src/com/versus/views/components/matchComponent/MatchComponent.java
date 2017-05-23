package com.versus.views.components.matchComponent;

import com.versus.model.Match;
import com.versus.model.MatchResult;
import com.versus.model.interfaces.MatchUpdatedListener;
import com.versus.views.editMatchView.EditMatchView;
import com.versus.views.editMatchView.EditMatchViewPanelSubmittedListener;

import javax.swing.*;
import java.awt.*;

public class MatchComponent extends JPanel {

	private MatchComponentDetail details;

	public MatchComponent(Match match, MatchUpdatedListener callback) {

		super(new FlowLayout());

		this.details = new MatchComponentDetail(match);

		this.add(details);

		JButton button = new JButton("Editar");

		button.addActionListener(e -> {

			if (match.getLocalCompetitor().isPresent() && match.getVisitorCompetitor().isPresent()) {

				new EditMatchView(match, new EditMatchViewPanelSubmittedListener() {
					@Override
					public void onSuccess(MatchResult newResult) {
						details.setResult(newResult);
						callback.onMatchUpdated(match);
					}

					@Override
					public void onError(Throwable cause) {
						cause.printStackTrace();
					}
				});

			}

		});

		this.add(button);

		this.setVisible(true);

	}
}
