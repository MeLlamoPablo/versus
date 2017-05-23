package com.versus.views.editMatchView;

import com.versus.model.Match;
import com.versus.model.MatchResult;
import com.versus.views.support.JFrameX;

public class EditMatchView extends JFrameX {

	private static int WIDTH = 300;
	private static int HEIGHT = 100;

	public EditMatchView(Match match, EditMatchViewPanelSubmittedListener callback) {

		super("Editar partido", WIDTH, HEIGHT);

		this.add(new EditMatchViewPanel(match, new EditMatchViewPanelSubmittedListener() {
			@Override
			public void onSuccess(MatchResult newResult) {
				EditMatchView.this.close();
				callback.onSuccess(newResult);
			}

			@Override
			public void onError(Throwable cause) {
				EditMatchView.this.close();
				callback.onError(cause);
			}
		}));

		this.setVisible(true);

	}

}
