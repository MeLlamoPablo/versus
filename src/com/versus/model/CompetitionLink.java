package com.versus.model;

public class CompetitionLink {

	private Competition target;
	private int spots;

	public Competition getTarget() {
		return target;
	}

	public void setTarget(Competition target) {
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
