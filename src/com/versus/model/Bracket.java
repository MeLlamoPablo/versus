package com.versus.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bracket {

	private List<Round> rounds = new ArrayList<>();

	public List<Round> getRounds() {
		return this.rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public Round getRound(int index) {
		return this.rounds.get(index);
	}

	public Bracket(List<Round> rounds) {
		this.rounds = rounds;
	}

	public static Bracket generateFor(List<Competitor> competitors, Competition competition) {

		// TODO generar para nºs distintos de potencias de dos

		int numberOfRounds = log(2, competitors.size());

		List<Round> rounds = new ArrayList<>();

		for (int i = 0; i < numberOfRounds; i++) {

			Round round = new Round();

			int numberOfMatches = (int) Math.pow(2, i);

			for (int j = 0; j < numberOfMatches; j++) {

				Match match = new Match();

				match.setCompetition(competition);

				round.addMatch(match);

			}

			rounds.add(round);

		}

		// So that the round with the most competitors is the first and not the last.
		Collections.reverse(rounds);

		rounds.get(0).addCompetitors(competitors);

		for (int i = 0; i < rounds.size(); i++) {

			if ((i + 1) < rounds.size()) {

				rounds.get(i).linkToNextRound(rounds.get(i + 1));

			}

		}

		return new Bracket(rounds);

	}

	@SuppressWarnings("SameParameterValue")
	private static int log(int base, int x) {
		return (int) (Math.log(x) / Math.log(base));
	}

}
