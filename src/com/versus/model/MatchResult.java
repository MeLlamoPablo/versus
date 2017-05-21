package com.versus.model;

public class MatchResult {

	private int localScore;
	private int visitorScore;

	public int getLocalScore() {
		return localScore;
	}

	public void setLocalScore(int localScore) {
		this.localScore = localScore;
	}

	public int getVisitorScore() {
		return visitorScore;
	}

	public void setVisitorScore(int visitorScore) {
		this.visitorScore = visitorScore;
	}

	public MatchResult(int localScore, int visitorScore) {
		this.setLocalScore(localScore);
		this.setVisitorScore(visitorScore);
	}

	public EMatchWinner get() {
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