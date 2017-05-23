package com.versus.views.editMatchView;

import com.versus.model.MatchResult;

public interface EditMatchViewPanelSubmittedListener {

	void onSuccess(MatchResult newResult);

	void onError(Throwable cause);

}
