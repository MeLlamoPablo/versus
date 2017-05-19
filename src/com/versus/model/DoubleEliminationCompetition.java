package com.versus.model;

public class DoubleEliminationCompetition extends EliminationCompetition {

	private Bracket upperBracket;
	private Bracket lowerBracket;
	private Match finals;

	public Bracket getUpperBracket() {
		return upperBracket;
	}

	public void setUpperBracket(Bracket upperBracket) {
		this.upperBracket = upperBracket;
	}

	public Bracket getLowerBracket() {
		return lowerBracket;
	}

	public void setLowerBracket(Bracket lowerBracket) {
		this.lowerBracket = lowerBracket;
	}

	public Match getFinals() {
		return finals;
	}

	public void setFinals(Match finals) {
		this.finals = finals;
	}

	public DoubleEliminationCompetition(String name) {
		super(name);
	}

	public void generateBrackets() throws Exception {

		if (this.getCompetitors().size() < 4) {
			throw new Exception("Cannot generate a double elimination competition for a tournament for " +
				"less than four players.");
		}

		Bracket upperBracket = Bracket.generateFor(this.getCompetitors(), this);
		Bracket lowerBracket = upperBracket.generateLowerBracket();

		upperBracket.setBracketEndedListener(this);
		lowerBracket.setBracketEndedListener(this);

		this.setUpperBracket(upperBracket);
		this.setLowerBracket(lowerBracket);

	}

	@Override
	void sendCompetitorsToNextCompetition() {
		// TODO
	}

	@Override
	public void onBracketEnded(Competitor winner, Bracket bracket) {

		// TODO

	}

}
