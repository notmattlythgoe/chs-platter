package com.mlythg.helix.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChsTeam {
	
	private int number;
	private String teamKey;
	private String name;
	private Map<Integer, List<ChsEvent>> events = new HashMap<>();
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTeamKey() {
		return teamKey;
	}

	public void setTeamKey(String teamKey) {
		this.teamKey = teamKey;
	}
	
	public void addEvent(ChsEvent chsEvent) {
		List<ChsEvent> eventYear = events.get(chsEvent.getYear());
		if (eventYear == null) {
			List<ChsEvent> eventList = new ArrayList<>();
			eventList.add(chsEvent);
			events.put(chsEvent.getYear(), eventList);
		} else {
			eventYear.add(chsEvent);
		}
	}
	
	private String getYearAverages() {
		StringBuilder eventRanks = new StringBuilder();
		List<Integer> years = new ArrayList<>(events.keySet());
		Collections.sort(years);
		Collections.reverse(years);
		for (Integer year : years) {
			List<ChsEvent> yearEvents = events.get(year);
			double sum = 0;
			for (ChsEvent event : yearEvents){
				sum += event.getRankPercent();
			}
			eventRanks.append(sum / yearEvents.size()).append("\t");
		}
		return eventRanks.toString();
	}
	
	private double getAllYearAverage() {
		double sum = 0;
		double numEvents = 0;
		for (List<ChsEvent> yearEvents : events.values()) {
			for (ChsEvent event : yearEvents) {
				if (event.getRankPercent() > 0) {
					numEvents++;
					sum += event.getRankPercent();
				}
			}
		}
		if (numEvents == 0) {
			return 1;
		}
		return sum / numEvents;
	}
	
	private double getPlayoffScore() {
		double sum = 0;
		double numEvents = 0;
		for (List<ChsEvent> yearEvents : events.values()) {
			for (ChsEvent event : yearEvents) {
				switch (event.getPlayoffPosition()) {
				case 1:
				case 2:
					sum += 2;
					break;
				case 3:
					sum += 1;
					break;
				default: sum +=  0;
				}
				numEvents++;
			}
		}
		if (numEvents == 0) {
			return 0;
		}
		return sum / numEvents;
	}
	
	public String toString() {
		StringBuilder data = new StringBuilder();
		data.append(number).append("\t")
		.append(name).append("\t")
		.append(getPlayoffScore()).append("\t")
		.append(getAllYearAverage()).append("\t")
		.append(getYearAverages());
		return data.toString();
	}
}
