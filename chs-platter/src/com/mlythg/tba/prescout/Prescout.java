package com.mlythg.tba.prescout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.plnyyanks.tba.apiv2.APIv2Helper;
import com.plnyyanks.tba.apiv2.interfaces.APIv2;
import com.plnyyanks.tba.apiv2.models.Event;
import com.plnyyanks.tba.apiv2.models.Team;

public class Prescout {

	public static void main(String[] args) {
		APIv2Helper.setAppId("frc2363:prescout:v0.1");
		APIv2 api = APIv2Helper.getAPI();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String eventCode = "vahay";
		int yearsToAverage = 3;
		try {
			System.out.print("Event Code: ");
			eventCode = br.readLine();

			System.out.print("Years: ");
			yearsToAverage = Integer.valueOf(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		List<Team> teams = api.fetchEventTeams(year + eventCode, null);
		PrintWriter writer;
		try {
			writer = new PrintWriter("prescout.xls", "UTF-8");
			for (Team team : teams) {
				StringBuilder eventRanks = new StringBuilder();
				eventRanks.append(team.getTeam_number()).append('\t').append(team.getNickname()).append('\t');
				double ranks = 0;
				double numEvents = 0;
				for (int i = 0; i < yearsToAverage; i++) {
					List<Event> events = api.fetchTeamEvents("frc" + team.getTeam_number(), year - i, null);
					for (Event event : events) {
						JsonArray rankings = api.fetchEventRankings(event.getYear() + event.getEvent_code(), null);
						if (rankings.size() > 0) {
							rankings.remove(0);
							for (JsonElement element : rankings) {
								if (element.getAsJsonArray().get(1).getAsInt() == team.getTeam_number()) {
									if (i == 0) {
										ranks += (element.getAsJsonArray().get(0).getAsDouble() / rankings.size()) * 5;
										numEvents += 5; 
									} else {
										ranks += (element.getAsJsonArray().get(0).getAsDouble() / rankings.size()) * (yearsToAverage - i);
										numEvents += yearsToAverage - i; 
									}
								}
							}
						}
					}
				}
				eventRanks.append(ranks / numEvents);
				System.out.println(eventRanks.toString());
				writer.println(eventRanks.toString());
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
