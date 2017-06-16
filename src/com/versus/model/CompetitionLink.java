package com.versus.model;

import com.versus.model.exceptions.NullInputException;

/**
 * Un CompetitionLink (enlace entre competiciones) permite crear jerarquías entre competiciones,
 * mediante las cuales los X primeros competidores de la primera competición son automáticamente
 * añaidos como competidores en la segunda. X viene dado por el parámetro spots.
 */
public class CompetitionLink extends Entity {

	private Competition target;
	private int spots;

	public Competition getTarget() {
		return target;
	}

	public void setTarget(Competition target) {
		if (target == null) {
			throw new NullInputException("The target of a CompetitionLink can't be null!");
		}

		this.target = target;
	}

	public int getSpots() {
		return spots;
	}

	public void setSpots(int spots) {
		this.spots = spots;
	}

	public CompetitionLink(Competition target, int spots) {
		this.setTarget(target);
		this.setSpots(spots);
	}

}
