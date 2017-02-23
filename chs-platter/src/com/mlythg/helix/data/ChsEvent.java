package com.mlythg.helix.data;

public class ChsEvent {
	
	private double rankPercent;
	private int year;
	private int playoffPosition;
	
	public double getRankPercent() {
		return rankPercent;
	}
	
	public void setRankPercent(double rank) {
		this.rankPercent = rank;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getPlayoffPosition() {
		return playoffPosition;
	}
	
	public void setPlayoffPosition(int playoffPosition) {
		this.playoffPosition = playoffPosition;
	}
}
