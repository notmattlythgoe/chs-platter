package com.mlythg.mabos.api.models;

import java.util.List;

public class Dynasty {
    private String team;
    private double score;
    private List<DynastyEvent> history;

    Dynasty(){ }

	public String getTeam() {
		return team;
	}

	void setTeam(String team) {
		this.team = team;
	}

	public List<DynastyEvent> getHistory() {
		return history;
	}

	void setHistory(List<DynastyEvent> scores) {
		this.history = scores;
	}
	
	public double getScore() {
		return score;
	}

	void setScore(double score) {
		this.score = score;
	}
}
