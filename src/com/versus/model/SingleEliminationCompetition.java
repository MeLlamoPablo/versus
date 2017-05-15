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

	public Bracket generateBracket() {
		this.setBracket(Bracket.generateFor(this.getCompetitors(), this));
		return this.getBracket();
	}

}
