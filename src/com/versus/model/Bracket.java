package com.versus.model;

import com.versus.model.interfaces.BracketEndedListener;
import com.versus.model.interfaces.RoundEndedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bracket implements RoundEndedListener {

	private List<Round> rounds = new ArrayList<>();
	private BracketEndedListener bracketEndedListener;
	private boolean finished = false;

	public List<Round> getRounds() {
		return this.rounds;
	}

	void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public Round getRound(int index) {
		return this.rounds.get(index);
	}

	public boolean isFinished() {
		return finished;
	}

	private void setFinished(boolean finished) {
		this.finished = finished;
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

		// La primera ronda siempre va a ser una ronda con los perdedores de la primera ronda del cuadro superior.
		Round lowerBracketFirstRound = upperBracketRounds.get(0).generateLosersRound();
		lowerBracketRounds.add(lowerBracketFirstRound);

		// La segunda ronda siempre va a ser una ronda con los ganadores de la primera, y los perdedores de la
		// segunda del cuadro superior. Va a tener el mismo núemro de partidas que la primera.
		Competition currentCompetition = lowerBracketFirstRound.getMatch(0).getCompetition();
		Round upperBracketSecondRound = upperBracketRounds.get(1);

		lowerBracketRounds.add
			(Round.fromUpperAndLowerBracketRounds(upperBracketSecondRound, lowerBracketFirstRound, currentCompetition));

		// A partir de aquí, por cada ronda del cuadro superior crearemos dos rondas del cuadro inferior. En la
		// primera, los del cuadro superior no bajan; es decir, el número de competidores en el cuadro inferior se
		// reduce en dos. A esta ronda le vamos a llamar roundWithoutDescent.
		// En la segunda, los del cuadro superior descienden, y por lo tanto el número de competidores en el cuadro
		// inferior se mantiene. A esta ronda le vamos a llamar roundWithDescent.
		int upperBracketRoundsLeft = upperBracketRounds.size() - 2;

		for (int i = 0; i < upperBracketRoundsLeft; i++) {

			// La cantidad de partidas en currentUpperBracketRound es la misma que la que va a haber tanto en
			// roundWithoutDescent como en roundWithDescent
			Round currentUpperBracketRound = upperBracketRounds.get(i + 2), roundWithoutDescent, roundWithDescent;
			List<Match> currentUpperBracketRoundMatches = currentUpperBracketRound.getMatches();

			roundWithoutDescent = new Round();

			for (int j = 0; j < currentUpperBracketRoundMatches.size(); j++) {

				Match match = new Match();
				match.setCompetition(currentCompetition);
				roundWithoutDescent.addMatch(match);

			}

			lowerBracketRounds.get(lowerBracketRounds.size() - 1).linkToNextRound(roundWithoutDescent);

			roundWithDescent = Round
				.fromUpperAndLowerBracketRounds(currentUpperBracketRound, roundWithoutDescent, currentCompetition);

			lowerBracketRounds.add(roundWithoutDescent);
			lowerBracketRounds.add(roundWithDescent);

		}

		return lowerBracket;

	}

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
			this.setFinished(true);
		}

	}
}
