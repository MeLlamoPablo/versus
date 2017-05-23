package com.versus.views.editMatchView;

import com.versus.model.Match;
import com.versus.model.MatchResult;
import com.versus.model.exceptions.BadInputException;

import javax.swing.*;
import java.awt.*;

class EditMatchViewPanel extends JPanel {

	EditMatchViewPanel(Match match, EditMatchViewPanelSubmittedListener callback) {

		super(new FlowLayout());

		assert match.getLocalCompetitor().isPresent();
		assert match.getVisitorCompetitor().isPresent();

		// FORM

		JPanel form = new JPanel(new GridLayout(2, 2));

		JTextField localScore = new JTextField();
		JTextField visitorScore = new JTextField();

		form.add(new JLabel(match.getLocalCompetitor().get().getName()));
		form.add(localScore);

		form.add(new JLabel(match.getVisitorCompetitor().get().getName()));
		form.add(visitorScore);

		// BUTTON

		JButton button = new JButton("Guardar");

		button.addActionListener(e -> {

			try {

				int local = Integer.valueOf(localScore.getText());
				int visitor = Integer.valueOf(visitorScore.getText());

				MatchResult newResult = new MatchResult(local, visitor);

				match.setResult(newResult);
				callback.onSuccess(newResult);

			} catch (NumberFormatException | BadInputException ex) {

				callback.onError(ex);

			}

		});

		this.add(form);
		this.add(button);

		this.setVisible(true);

	}
}
