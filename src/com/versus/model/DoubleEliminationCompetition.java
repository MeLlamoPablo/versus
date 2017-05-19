package com.versus.model;

import com.versus.model.interfaces.MatchUpdatedListener;

import java.util.List;

public class DoubleEliminationCompetition extends EliminationCompetition implements MatchUpdatedListener {

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

	private void setFinals(Match finals) {
		this.finals = finals;
	}

	public DoubleEliminationCompetition(String name) {
		super(name);
	}

	/**
	 * Genera un cuadro superior, un cuadro inferior, y una gran final para esta competición.
	 * @throws Exception Si el número de jugadores es incorrecto.
	 */
	public void generateBrackets() throws Exception {

		if (this.getCompetitors().size() < 4) {
			throw new Exception("Cannot generate a double elimination competition for a tournament for " +
				"less than four players.");
		}

		Bracket upperBracket = Bracket.generateFor(this.getCompetitors(), this);
		Bracket lowerBracket = upperBracket.generateLowerBracket();

		upperBracket.setBracketEndedListener(this);
		lowerBracket.setBracketEndedListener(this);

		List<Round> upperBracketRounds = upperBracket.getRounds();
		List<Round> lowerBracketRounds = lowerBracket.getRounds();

		MatchLink upperBracketFinalsLink = upperBracketRounds
			.get(upperBracketRounds.size() - 1)
			.getMatch(0) // En la última ronda solo hay una partida
			.getLink();

		MatchLink lowerBracketFinalsLink = lowerBracketRounds
			.get(lowerBracketRounds.size() - 1)
			.getMatch(0) // En la última ronda solo hay una partida
			.getLink();

		Match grandFinals = new Match();

		upperBracketFinalsLink.setWinnerTarget(grandFinals);
		upperBracketFinalsLink.setWinnerPosition(MatchLink.EMatchPosition.LOCAL);

		lowerBracketFinalsLink.setLoserTarget(grandFinals);
		lowerBracketFinalsLink.setWinnerPosition(MatchLink.EMatchPosition.VISITOR);

		grandFinals.setMatchUpdatedListener(this);
		grandFinals.setCompetition(this);

		this.setUpperBracket(upperBracket);
		this.setLowerBracket(lowerBracket);
		this.setFinals(grandFinals);

	}

	@Override
	public void onBracketEnded(Competitor winner, Bracket bracket) {

		if (this.getUpperBracket().isFinished() && this.getLowerBracket().isFinished()) {

			// TODO

		}

	}

	@Override
	// Llamado cuando se ha añadido un resultado a la final.
	public void onMatchUpdated(Match grandFinals) {

		Competitor winner = grandFinals.getWinner();
		this.sendCompetitorsToNextCompetition();

		if (this.getCompetitionEndedListener() != null) {
			this.getCompetitionEndedListener().onWinner(winner);
		}

	}
}
