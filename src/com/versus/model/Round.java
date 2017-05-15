package com.versus.model;

import java.util.ArrayList;
import java.util.List;

public class Round extends Entity {

	private List<Match> matches = new ArrayList<>();

	public void addMatch(Match match) {
		this.matches.add(match);
	}

	public List<Match> getMatches() {
		return matches;
	}

	public Match getMatch(int index) {
		return this.matches.get(index);
	}

	public void addCompetitors(List<Competitor> competitors) {

		for (int i = 0; i < (this.matches.size() * 2); i += 2) {

			this.matches.get(i / 2).setLocalCompetitor(competitors.get(i));
			this.matches.get(i / 2).setVisitorCompetitor(competitors.get(i + 1));

		}

	}

	public void linkToNextRound(Round nextRound) {

		for (int i = 0; i < this.matches.size(); i++) {

			MatchLink matchLink = this.matches.get(i).getLink();

			matchLink.setWinnerTarget(nextRound.getMatches().get(i / 2));
			matchLink.setWinnerPosition(
				(i % 2 == 0) ? MatchLink.EMatchPosition.LOCAL : MatchLink.EMatchPosition.VISITOR);

		}

	}

	public void updateLinks() {

		for (Match match : this.matches) {

			MatchResult result = match.getResult();

			if (result != null) {

				MatchLink link = match.getLink();
				Match winnerTarget = link.getWinnerTarget();
				Match loserTarget = link.getLoserTarget();

				if (!link.isWinnerLinkFulfilled() && winnerTarget != null) {

					if (link.getWinnerPosition() == MatchLink.EMatchPosition.LOCAL) {

						winnerTarget.setLocalCompetitor(match.getWinner());

					} else {

						winnerTarget.setVisitorCompetitor(match.getWinner());

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

		}

	}

}
