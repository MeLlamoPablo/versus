package com.versus.model;

public class SingleEliminationCompetition extends EliminationCompetition {

	private Bracket bracket;

	public Bracket getBracket() {
		return bracket;
	}

	private void setBracket(Bracket bracket) {
		this.bracket = bracket;
	}

	public SingleEliminationCompetition(String name) {
		super(name);
	}

	public Bracket generateBracket() throws Exception {

		Bracket bracket = Bracket.generateFor(this.getCompetitors(), this);
		bracket.setBracketEndedListener(this);
		this.setBracket(bracket);

		return bracket;

	}

	@Override
	public void onBracketEnded(Competitor winner, Bracket bracket) {

		if (this.getCompetitionEndedListener() != null) {
			this.getCompetitionEndedListener().onWinner(winner);
		}

	}

}
