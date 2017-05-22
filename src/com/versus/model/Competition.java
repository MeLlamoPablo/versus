package com.versus.model;

import com.versus.model.exceptions.BadInputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

abstract class Competition extends Entity {

	private String name;
	private List<Competitor> competitors = new ArrayList<>();
	private CompetitionLink link;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCompetitor(Competitor competitor) {
		this.competitors.add(competitor);
	}

	public List<Competitor> getCompetitors() {
		return competitors;
	}

	public Optional<CompetitionLink> getLink() {
		return Optional.ofNullable(link);
	}

	public void setLink(Competition target, int spots) {
		this.link = new CompetitionLink(target, spots);
	}

	public void setLink(CompetitionLink link) throws BadInputException {
		this.link = link;
	}

	/**
	 * Regenera el orden de los competidores de forma aleatoria.
	 */
	protected void shuffleSeeds() {
		Collections.shuffle(this.competitors);
	}

	public Competition(String name) {
		this.setName(name);
	}

	/**
	 * Método que debe validar si el resultado de una partida es válido para una competición.
	 * En caso de no serlo, al intentar insertarlo se lanzará una excepción.
	 *
	 * @param result El resultado que se pretende insertar en una de las partidas de la competición.
	 * @return True si es válido, o falso si no lo es.
	 */
	abstract boolean isResultValid(MatchResult result);

	abstract void sendCompetitorsToNextCompetition();
}
