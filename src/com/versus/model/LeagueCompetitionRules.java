package com.versus.model;

/**
 * Clase que contiene las reglas de una liga, como por ejemplo la cantidad de puntos que otorga una victoria.
 */
public class LeagueCompetitionRules {

	private int pointsPerWin;
	private int pointsPerDraw;
	private int pointsPerLoss;
	/**
	 * Indica si se pueden modificar los valores del objeto. Una vez comience la competición, esto se pondrá en false.
	 */
	private boolean modifiable;

	public int getPointsPerWin() {
		return pointsPerWin;
	}

	public void setPointsPerWin(int pointsPerWin) {
		throwIfUnmodifiable();
		this.pointsPerWin = pointsPerWin;
	}

	public int getPointsPerDraw() {
		return pointsPerDraw;
	}

	public void setPointsPerDraw(int pointsPerDraw) {
		throwIfUnmodifiable();
		this.pointsPerDraw = pointsPerDraw;
	}

	public int getPointsPerLoss() {
		return pointsPerLoss;
	}

	public void setPointsPerLoss(int pointsPerLoss) {
		throwIfUnmodifiable();
		this.pointsPerLoss = pointsPerLoss;
	}

	public boolean isModifiable() {
		return modifiable;
	}

	void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

	public LeagueCompetitionRules() {
		this(3, 1, 0);
	}

	public LeagueCompetitionRules(int pointsPerWin, int pointsPerDraw, int pointsPerLoss) {
		this.setPointsPerWin(pointsPerWin);
		this.setPointsPerDraw(pointsPerDraw);
		this.setPointsPerLoss(pointsPerLoss);
		this.setModifiable(true);
	}

	private void throwIfUnmodifiable() {

		if (this.isModifiable()) {

			throw new UnmodifiableException("Cannot modify the content of this rule set!");

		}

	}

	private class UnmodifiableException extends RuntimeException {

		private UnmodifiableException(String message) {
			super(message);
		}
	}
}
