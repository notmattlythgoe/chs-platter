package com.mlythg.mabos.api.models;

public class DynastyEvent {
    private String id;
    private String eventKey;
    private String matchLevel;
    private boolean won;
    private double score;
    private int year;

    DynastyEvent(){ }

	public String getEventKey() {
		return eventKey;
	}

	void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public double getScore() {
		return score;
	}

	void setScore(double score) {
		this.score = score;
	}

	public String getMatchLevel() {
		return matchLevel;
	}

	void setMatchLevel(String matchLevel) {
		this.matchLevel = matchLevel;
	}

	public boolean isWon() {
		return won;
	}

	void setWon(boolean won) {
		this.won = won;
	}

	public int getYear() {
		return year;
	}

	void setYear(int year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}
}
