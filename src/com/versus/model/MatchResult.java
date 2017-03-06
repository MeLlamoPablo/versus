package com.versus.model;

public class MatchResult {

	private int localScore;
	private int visitorScore;

	public int getLocalScore() {
		return localScore;
	}

	public MatchResult setLocalScore(int localScore) {
		this.localScore = localScore;
		return this;
	}

	public int getVisitorScore() {
		return visitorScore;
	}

	public MatchResult setVisitorScore(int visitorScore) {
		this.visitorScore = visitorScore;
		return this;
	}

	public MatchResult(int localScore, int visitorScore) {
		this.setLocalScore(localScore);
		this.setVisitorScore(visitorScore);
	}

	/**
	 * Calculates who is the winner of the match.
	 * @return EMatchWinner
	 */
	public EMatchWinner getWinner() {
		// TODO lanzar excepción si no está definido el resultado o los jugadores
		if (this.getLocalScore() > this.getVisitorScore()) {

			return EMatchWinner.LOCAL;

		} else if (this.getLocalScore() < this.getVisitorScore()) {

			return EMatchWinner.VISITOR;

		} else {

			return EMatchWinner.DRAW;

		}
	}

	public enum EMatchWinner { LOCAL, VISITOR, DRAW }
}