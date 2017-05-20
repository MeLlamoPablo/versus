package com.versus.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeagueCompetition extends Competition {

	private List<Round> rounds;
	// Two legs = Ida y vuelta.
	private boolean twoLegs;

	public List<Round> getRounds() {
		return rounds;
	}

	public Round getRound(int index) {
		return rounds.get(index);
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	private boolean isTwoLegs() {
		return twoLegs;
	}

	public void setTwoLegs(boolean twoLegs) {
		this.twoLegs = twoLegs;
	}

	public LeagueCompetition(String name) {
		this(name, false);
	}

	public LeagueCompetition(String name, boolean twoLegs) {
		super(name);

		this.setTwoLegs(twoLegs);
	}

	public void generateRounds() throws Exception {

		// Creamos una copia de this.getCompetitors porque los iremos rotando;
		// no queremos modificar el orden del arraylist original.
		List<Competitor> competitors = new ArrayList<>(this.getCompetitors());
		int numberOfCompetitors = competitors.size();

		if (numberOfCompetitors % 2 != 0) {
			throw new Exception("Can't make a league with an odd number of competitors!");
		}

		List<Round> firstLeg = new ArrayList<>();
		List<Round> secondLeg = new ArrayList<>();

		// Una liga tiene tantas jornadas como competidores, restando uno (el que jugaría contra sí mismo)
		int numberOfRounds = numberOfCompetitors - 1;
		// Una jornada tiene la mitad de partidas que competidores.
		int matchesPerRound = numberOfCompetitors / 2;

		// Para generar los partidos, emparejaremos a los jugadores de dos en dos. Es decir, en la primera ronda,
		// suponiendo que hay cuatro jugadores, los partidos serán 1 vs 2 y 3 vs 4. Tras ello, rotaremos a los
		// jugadores, a excepción del primero, que se queda siempre en su sitio. Es decir, la segunda ronda sería
		// 1 vs 4 y 2 vs 3

		// Aleatorizamos el orden de los competidores para que el orden de registro no influya en la primera ronda.
		Collections.shuffle(competitors);

		for (int i = 0; i < numberOfRounds; i++) {

			// Generar las rondas

			Round firstLegRound = new Round();
			Round secondLegRound = new Round();

			for (int j = 0, k = 0; j < matchesPerRound; j++, k+=2) {

				Match firstLegMatch = new Match();
				firstLegMatch.setCompetition(this);

				firstLegMatch.setLocalCompetitor(competitors.get(k));
				firstLegMatch.setVisitorCompetitor(competitors.get(k + 1));

				firstLegRound.addMatch(firstLegMatch);

				// Si la liga es de ida y vuelta, hacemos lo mismo, pero invirtiendo el local y el visitante.
				if (this.isTwoLegs()) {

					Match secondLegMatch = new Match();
					secondLegMatch.setCompetition(this);

					secondLegMatch.setLocalCompetitor(competitors.get(k + 1));
					secondLegMatch.setVisitorCompetitor(competitors.get(k));

					secondLegRound.addMatch(secondLegMatch);

				}

			}

			// Distribuimos las partidas de forma aleatoria para que el primer competidor (el que se queda fijo)
			// no aparezca siempre el primero.
			Collections.shuffle(firstLegRound.getMatches());
			firstLeg.add(firstLegRound);

			if (this.isTwoLegs()) {
				Collections.shuffle(secondLegRound.getMatches());
				secondLeg.add(secondLegRound);
			}

			// Rotar a los jugadores

			List<Rotation> rotations = new ArrayList<>();

			// Empezamos en 1 porque el jugador de la posición 0 nunca lo cambiamos.
			for (int j = 1; j < numberOfCompetitors; j++) {

				rotations.add(
					new Rotation(competitors.get(j), ((j + 1) < numberOfCompetitors) ? (j + 1) : 1)
				);

			}

			Rotation.performAll(rotations, competitors);

		}

		if (this.isTwoLegs()) {

			firstLeg.addAll(secondLeg);

		}

		this.rounds = firstLeg;

	}

	public void getRankedCompetitor(Competitor competitor) {

		RankedCompetitor rCompetitor = new RankedCompetitor(competitor);

		this.rounds
			.stream()
			.map(
				round -> round
					.getMatches()
					.stream()
					.filter(match ->
            			match.getLocalCompetitor().equals(competitor) || match.getVisitorCompetitor().equals(competitor)
        )); // TODO

	}

	@Override
	// En una liga todos los resultados son válidos, incluidos los empates
	boolean isResultValid(MatchResult result) {
		return true;
	}

	@Override
	void sendCompetitorsToNextCompetition() {
		// TODO
	}

	/**
	 * Rotation es una clase helper que hará más fácil la tarea de rotar los jugadores. En una rotación se asigna el
	 * siguiente índice a un jugador, y performAll efectúa todas las rotaciones.
	 */
	private static class Rotation {

		Competitor competitor;
		int nextIndex;

		private Rotation(Competitor competitor, int nextIndex) {
			this.competitor = competitor;
			this.nextIndex = nextIndex;
		}

		private static void performAll(List<Rotation> rotations, List<Competitor> targetList) {

			rotations.forEach(rotation -> targetList.set(rotation.nextIndex, rotation.competitor));

		}

	}

}
