package com.versus.model;

public class SingleEliminationCompetition extends EliminationCompetition {

	private Bracket bracket;
	private Competitor winner;

	public Bracket getBracket() {
		return bracket;
	}

	private void setBracket(Bracket bracket) {
		this.bracket = bracket;
	}

	@Override
	public void setLink(CompetitionLink link) throws Exception {
		if (link.getSpots() != 1) {
			throw new Exception("A Single Elimination Competition Link can only have one spot.");
		}

		super.setLink(link);
	}

	public Competitor getWinner() {
		return winner;
	}

	public void setWinner(Competitor winner) {
		this.winner = winner;
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
	public void sendCompetitorsToNextCompetition() {

		CompetitionLink link = this.getLink();

		if (link != null) {

			assert link.getSpots() == 1;

			link.getTarget().addCompetitor(this.getWinner());

		}


	}

	@Override
	public void onBracketEnded(Competitor winner, Bracket bracket) {

		this.setWinner(winner);
		this.sendCompetitorsToNextCompetition();

		if (this.getCompetitionEndedListener() != null) {
			this.getCompetitionEndedListener().onWinner(winner);
		}

	}

}
