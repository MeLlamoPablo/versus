package com.versus.model.interfaces;

import com.versus.model.Bracket;
import com.versus.model.Competitor;

public interface BracketEndedListener {

	void onBracketEnded(Competitor winner, Bracket bracket);

}
