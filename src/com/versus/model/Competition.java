package com.versus.model;

import com.versus.model.interfaces.CompetitionEndedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class Competition extends Entity {

	private String name;
	private List<Competitor> competitors = new ArrayList<>();
	private CompetitionLink link;
	private CompetitionEndedListener competitionEndedListener;

	public Competition setId() {
		return this;
	}

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

	public CompetitionLink getLink() {
		return link;
	}

	public void setLink(CompetitionLink link) {
		this.link = link;
	}

	protected CompetitionEndedListener getCompetitionEndedListener() {
		return competitionEndedListener;
	}

	public void setCompetitionEndedListener(CompetitionEndedListener competitionEndedListener) {
		this.competitionEndedListener = competitionEndedListener;
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
}
