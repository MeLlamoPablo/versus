package com.versus.model;

import java.util.*;
import java.util.stream.Collectors;

public class LeagueCompetition extends Competition {

	private List<Round> rounds;
	// Two legs = Ida y vuelta.
	private boolean twoLegs;
	private LeagueCompetitionRules ruleSet;

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

	public LeagueCompetitionRules getRuleSet() {
		return ruleSet;
	}

	private void setRuleSet(LeagueCompetitionRules ruleSet) {
		this.ruleSet = ruleSet;
	}

	public LeagueCompetition(String name) {
		this(name, false, new LeagueCompetitionRules());
	}

	public LeagueCompetition(String name, boolean twoLegs) {
		this(name, twoLegs, new LeagueCompetitionRules());
	}

	public LeagueCompetition(String name, boolean twoLegs, LeagueCompetitionRules ruleSet) {
		super(name);

		this.setTwoLegs(twoLegs);
		this.setRuleSet(ruleSet);
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

	/**
	 * Calcula los puntos, victorias, empates, derrotas, tantos anotados, y tantos anotados en contra del competidor
	 * dado.
	 * @param competitor El competidor sobre el que queremos realizar los cálculos.
	 * @return Un objeto RankedCompetitor que contiene todos estos datos.
	 */
	public RankedCompetitor getRankedCompetitor(Competitor competitor) {

		RankedCompetitor rCompetitor = new RankedCompetitor(competitor);

		this.rounds
			.stream()
			// Mapeamos la ronda a la partida que jugó el competidor en cuestión en dicha ronda
			.map(
				round -> {
					Optional<Match> matchOptional = round
						.getMatches()
						.stream()
						// Quitamos las partidas donde el competidor no jugó
						.filter(match ->
							competitor.equals(match.getLocalCompetitor().orElse(null))
							||
							competitor.equals(match.getVisitorCompetitor().orElse(null))
						)
						.findFirst();

					assert matchOptional.isPresent();
					return matchOptional.get();
				}
			)
			// Quitamos las partidas que no tienen resultado
			.filter(match -> match.getResult().isPresent())
			.forEach(match -> {

				// Añadimos las partidas ganadas, empatadas o perdidas, y los puntos correspondientes.

				Optional<Competitor> winner = match.getWinner();

				if (winner.isPresent()) {

					if (winner.get().equals(competitor)) {

						rCompetitor.addPoints(this.getRuleSet().getPointsPerWin());
						rCompetitor.addWins(1);

					} else {

						rCompetitor.addPoints(this.getRuleSet().getPointsPerLoss());
						rCompetitor.addLoses(1);

					}

				} else {

					// Si no está presente, la partida ha acabado en empate, ya que hemos quitado de la lista las
					// partidas donde no se ha reportado ningún resultado.
					rCompetitor.addPoints(this.getRuleSet().getPointsPerDraw());
					rCompetitor.addDraws(1);

				}

				// Añadimos los puntos que el competidor ha anotado, y los que le han anotado.

				// Estas assertions deberían ser siempre true porque hemos filtrado los nulos antes.
				assert match.getResult().isPresent();
				assert match.getLocalCompetitor().isPresent();
				MatchResult result = match.getResult().get();

				if (match.getLocalCompetitor().get().equals(competitor)) {

					rCompetitor.addScored(result.getLocalScore());
					rCompetitor.addScoredAgainst(result.getVisitorScore());

				} else {

					rCompetitor.addScored(result.getVisitorScore());
					rCompetitor.addScoredAgainst(result.getLocalScore());

				}

			});

		return rCompetitor;

	}

	/**
	 * @return La clasificación, es decir, una lista de objetos RankedCompetitor, ordenados por puntos
	 * totales descendientemente.
	 */
	public List<RankedCompetitor> getRanking() {
		return this.getCompetitors()
			.stream()
			.map(this::getRankedCompetitor)
			.sorted(Comparator.comparingInt(RankedCompetitor::getPoints).reversed())
			.collect(Collectors.toCollection(ArrayList::new));
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
