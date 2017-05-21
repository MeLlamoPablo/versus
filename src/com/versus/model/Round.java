package com.versus.model;

import com.versus.model.interfaces.MatchUpdatedListener;
import com.versus.model.interfaces.RoundEndedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Round extends Entity implements MatchUpdatedListener {

	private List<Match> matches = new ArrayList<>();
	private RoundEndedListener roundEndedListener;

	public void addMatch(Match match) {

		match.setMatchUpdatedListener(this);
		this.matches.add(match);

	}

	public List<Match> getMatches() {
		return matches;
	}

	public Match getMatch(int index) {
		return this.matches.get(index);
	}

	private RoundEndedListener getRoundEndedListener() {
		return roundEndedListener;
	}

	public void setRoundEndedListener(RoundEndedListener roundEndedListener) {
		this.roundEndedListener = roundEndedListener;
	}

	/**
	 * @return False si quedan partidas con resultados todavía por reportar, true de lo contrario.
	 */
	public boolean hasEnded() {

		return this.matches.stream().filter(match -> match.getResult() == null).count() == 0;

	}

	/**
	 * Añade una lista de competidores a esta ronda.
	 *
	 * @param competitors Los competidores a añadir. El tamaño de esta lista debe de ser el doble del número de partidos
	 *                    que hay en esta ronda.
	 */
	void addCompetitors(List<Competitor> competitors) throws Exception {

		if (competitors.size() != this.matches.size() * 2) {
			throw new Exception("The number of competitors must be exactly twice as the number of matches.");
		}

		for (int i = 0; i < (this.matches.size() * 2); i += 2) {

			this.matches.get(i / 2).setLocalCompetitor(competitors.get(i));
			this.matches.get(i / 2).setVisitorCompetitor(competitors.get(i + 1));

		}

	}

	/**
	 * Crea un enlace de esta ronda a la siguiente. Esto permite que los ganadores de las partidas de esta ronda
	 * pasen automáticamente a la siguiente. El método updateLinks() se encargará de añadir a los competidores
	 * ganadores a sus correspondientes partidas, una vez su partida correspondiente en esta ronda tenga un resultado
	 * (MatchResult) que no sea null, ni empate.
	 *
	 * @param nextRound La siguiente Round en el Bracket actual.
	 */
	void linkToNextRound(Round nextRound) {

		for (int i = 0; i < this.matches.size(); i++) {

			MatchLink matchLink = this.matches.get(i).getLink();

			matchLink.setWinnerTarget(nextRound.getMatches().get(i / 2));
			matchLink.setWinnerPosition(
				(i % 2 == 0) ? MatchLink.EMatchPosition.LOCAL : MatchLink.EMatchPosition.VISITOR);

		}

	}

	/**
	 * @return Una ronda con partidas para los perdedores de esta ronda.
	 */
	Round generateLosersRound() {

		Round losersRound = new Round();
		// Asumimos que todas las partidas tienen la misma competición.
		Competition currentCompetition = this.matches.get(0).getCompetition();

		List<Match> losersRoundMatches = new ArrayList<>();

		// TODO hacer esto en un solo bucle
		for (int i = 0; i < this.matches.size() / 2; i++) {

			Match match = new Match();
			match.setCompetition(currentCompetition);

			losersRoundMatches.add(match);

		}

		for (int i = 0; i < this.matches.size(); i++) {

			MatchLink matchLink = this.matches.get(i).getLink();

			matchLink.setLoserTarget(losersRoundMatches.get(i / 2));
			matchLink.setLoserPosition(
				(i % 2 == 0) ? MatchLink.EMatchPosition.LOCAL : MatchLink.EMatchPosition.VISITOR);

		}

		losersRoundMatches.forEach(losersRound::addMatch);

		return losersRound;

	}

	/**
	 * Comprueba todos los enlaces de las partidas de esta ronda.
	 */
	public void updateLinks() {
		this.matches.forEach(this::updateLink);
	}

	/**
	 * Comprueba el enlace (MatchLink) de la partida (Match) especificada. En caso de que su resultado haya sido
	 * actualizado, establece al competidor (Competitor) ganador como competidor local o visitante de la partida del
	 * enlace. En caso de que el enlace tenga un destino para el competidor perdedor, hace lo propio con éste.
	 *
	 * @param match La partida cuyo enlace queremos actualizar
	 */
	private void updateLink(Match match) {

		MatchResult result = match.getResult();

		if (result != null) {

			MatchLink link = match.getLink();
			Match winnerTarget = link.getWinnerTarget();
			Match loserTarget = link.getLoserTarget();

			if (!link.isWinnerLinkFulfilled() && winnerTarget != null) {

				if (link.getWinnerPosition() == MatchLink.EMatchPosition.LOCAL) {

					winnerTarget.setLocalCompetitor(match.getWinner().orElse(null));

				} else {

					winnerTarget.setVisitorCompetitor(match.getWinner().orElse(null));

				}

				link.setWinnerLinkFulfilled(true);

			}

			if (!link.isLoserLinkFulfilled() && loserTarget != null) {

				if (link.getLoserPosition() == MatchLink.EMatchPosition.LOCAL) {

					loserTarget.setLocalCompetitor(match.getLoser());

				} else {

					loserTarget.setVisitorCompetitor(match.getLoser());

				}

				link.setLoserLinkFulfilled(true);

			}

		}

		callListener();

	}

	/**
	 * Llama al listener en caso de ser necesario.
	 */
	private void callListener() {

		if (this.getRoundEndedListener() != null && this.hasEnded()) {

			this.getRoundEndedListener().onRoundEnded(this);

		}

	}

	@Override
	public void onMatchUpdated(Match match) {
		this.updateLink(match);
	}

	/**
	 * Genera una ronda a partir de otras dos: una de un cuadro superior, y otra de un cuadro inferior. Los perdedores
	 * de la ronda del cuadro superior irán a la ronda que se va a generar como equipo local. Los ganadores de la
	 * ronda del cuadro inferior irán a la ronda que se va a generar como equipo visitante.
	 *
	 * @param upperBracketRound La ronda del cuadro superior.
	 * @param lowerBracketRound La ronda del cuadro inferior.
	 * @param competition La competición donde se está generando esta ronda.
	 * @return
	 */
	static Round fromUpperAndLowerBracketRounds(Round upperBracketRound, Round lowerBracketRound, Competition
		competition) {

		Round round = new Round();

		for (int i = 0; i < lowerBracketRound.getMatches().size(); i++) {

			Match match = new Match();
			match.setCompetition(competition);

			MatchLink fromUpperBracket = upperBracketRound.getMatch(i).getLink();
			MatchLink fromLowerBracket = lowerBracketRound.getMatch(i).getLink();

			fromUpperBracket.setLoserTarget(match);
			fromUpperBracket.setLoserPosition(MatchLink.EMatchPosition.LOCAL);

			fromLowerBracket.setWinnerTarget(match);
			fromLowerBracket.setWinnerPosition(MatchLink.EMatchPosition.VISITOR);

			round.addMatch(match);

		}

		return round;

	}
}
