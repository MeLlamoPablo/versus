package com.versus.model;

import com.versus.model.interfaces.BracketEndedListener;
import com.versus.model.interfaces.RoundEndedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bracket implements RoundEndedListener {

	private List<Round> rounds = new ArrayList<>();
	private BracketEndedListener bracketEndedListener;

	public List<Round> getRounds() {
		return this.rounds;
	}

	void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public Round getRound(int index) {
		return this.rounds.get(index);
	}

	public BracketEndedListener getBracketEndedListener() {
		return bracketEndedListener;
	}

	void setBracketEndedListener(BracketEndedListener bracketEndedListener) {
		this.bracketEndedListener = bracketEndedListener;
	}

	public Bracket(List<Round> rounds) {
		this.rounds = rounds;
	}

	/**
	 * Genera un cuadro inferior (lowerBracket) enlazado con el cuadro actual, para un torneo de eliminación doble
	 * (DoubleEliminationCompetition)
	 *
	 * @return El cuadro inferior generado, con las partidas enlazadas.
	 */
	public Bracket generateLowerBracket() {

		List<Round> upperBracketRounds = this.getRounds();
		List<Round> lowerBracketRounds = new ArrayList<>();

		Bracket lowerBracket = new Bracket(lowerBracketRounds);

		// La primera ronda siempre va a ser una ronda con los perdedores de la primera ronda del cuadro superior
		lowerBracketRounds.add(upperBracketRounds.get(0).generateLosersRound());

		// TODO

		return lowerBracket;

	}

	/*
	 * En los torneos de doble eliminación (DoubleEliminationCompetition), enlaza el cuadro superior (upperBracket) con
	 * el cuadro inferior (lowerBracket).
	 *
	 * @param lowerBracket El cuadro inferior de la misma competición que this.
	 */
	/*public void linkToLowerBracket(Bracket lowerBracket) {

		for (int i = 0; i < this.getRounds().size(); i++) {

			this.getRound(i).linkToLowerRound(lowerBracket.getRound(i));

		}

	}*/

	public static Bracket generateFor(List<Competitor> competitors, Competition competition) throws Exception {

		double numberOfRoundsWithDecimals = log(2, competitors.size());

		// Si numberOfRoundsWithDecimals tiene parte decimal.
		if (numberOfRoundsWithDecimals % 1 != 0) {
			throw new Exception("Generating brackets is only possible with a competitor list with a length equal to " +
				"a power of two (i.e: 2, 4, 8, 16...)");
		}

		int numberOfRounds = (int) numberOfRoundsWithDecimals;

		List<Round> rounds = new ArrayList<>();
		Bracket bracket = new Bracket(rounds);

		for (int i = 0; i < numberOfRounds; i++) {

			Round round = new Round();
			round.setRoundEndedListener(bracket);

			int numberOfMatches = (int) Math.pow(2, i);

			for (int j = 0; j < numberOfMatches; j++) {

				Match match = new Match();

				match.setCompetition(competition);
				round.addMatch(match);

			}

			rounds.add(round);

		}

		// Para que la ronda con el mayor número de competitors sea la primera y no la última
		Collections.reverse(rounds);

		rounds.get(0).addCompetitors(competitors);

		for (int i = 0; i < rounds.size(); i++) {

			if ((i + 1) < rounds.size()) {

				rounds.get(i).linkToNextRound(rounds.get(i + 1));

			}

		}

		return bracket;

	}

	@SuppressWarnings("SameParameterValue")
	private static double log(int base, int x) {
		return Math.log(x) / Math.log(base);
	}

	@Override
	public void onRoundEnded(Round round) {

		// Si la ronda es la final
		if (round.getMatches().size() == 1) {
			this.getBracketEndedListener().onBracketEnded(round.getMatch(0).getWinner(), this);
		}

	}
}
