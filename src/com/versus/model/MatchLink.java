package com.versus.model;

/**
 * Un MatchLink (enlace entre partidas) permite determinar el siguiente partido que jugarán los
 * competidores ganador y perdedor. Si esta definido, al llamar al método Match#setResult(), los
 * competidores serán asignados automáticamente al siguiente partido.
 */
public class MatchLink {

	private Match winnerTarget;
	private EMatchPosition winnerPosition;
	private boolean winnerLinkFulfilled = false;
	private Match loserTarget;
	private EMatchPosition loserPosition;
	private boolean loserLinkFulfilled = false;

	public Match getWinnerTarget() {
		return winnerTarget;
	}

	public void setWinnerTarget(Match winnerTarget) {
		this.winnerTarget = winnerTarget;
	}

	public EMatchPosition getWinnerPosition() {
		return winnerPosition;
	}

	public void setWinnerPosition(EMatchPosition winnerPosition) {
		this.winnerPosition = winnerPosition;
	}

	public boolean isWinnerLinkFulfilled() {
		return winnerLinkFulfilled;
	}

	public void setWinnerLinkFulfilled(boolean winnerLinkFulfilled) {
		this.winnerLinkFulfilled = winnerLinkFulfilled;
	}

	public Match getLoserTarget() {
		return loserTarget;
	}

	public void setLoserTarget(Match loserTarget) {
		this.loserTarget = loserTarget;
	}

	public EMatchPosition getLoserPosition() {
		return loserPosition;
	}

	public void setLoserPosition(EMatchPosition loserPosition) {
		this.loserPosition = loserPosition;
	}

	public boolean isLoserLinkFulfilled() {
		return loserLinkFulfilled;
	}

	public void setLoserLinkFulfilled(boolean loserLinkFulfilled) {
		this.loserLinkFulfilled = loserLinkFulfilled;
	}

	public enum EMatchPosition { LOCAL, VISITOR }

}
