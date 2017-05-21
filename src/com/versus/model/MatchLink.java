package com.versus.model;

import java.util.Optional;

/**
 * Un MatchLink (enlace entre partidas) permite determinar el siguiente partido que jugarán los
 * competidores ganador y perdedor. Si esta definido, al llamar al método Match#setResult(), los
 * competidores serán asignados automáticamente al siguiente partido.
 */
@SuppressWarnings("SameParameterValue")
public class MatchLink {

	private Match winnerTarget;
	private EMatchPosition winnerPosition;
	private boolean winnerLinkFulfilled = false;
	private Match loserTarget;
	private EMatchPosition loserPosition;
	private boolean loserLinkFulfilled = false;

	public Optional<Match> getWinnerTarget() {
		return Optional.ofNullable(winnerTarget);
	}

	public void setWinnerTarget(Match winnerTarget) {
		this.winnerTarget = winnerTarget;
	}

	public Optional<EMatchPosition> getWinnerPosition() {
		return Optional.ofNullable(winnerPosition);
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

	public Optional<Match> getLoserTarget() {
		return Optional.ofNullable(loserTarget);
	}

	public void setLoserTarget(Match loserTarget) {
		this.loserTarget = loserTarget;
	}

	public Optional<EMatchPosition> getLoserPosition() {
		return Optional.ofNullable(loserPosition);
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
