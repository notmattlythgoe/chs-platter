package com.mlythg.helix.prescout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mlythg.helix.data.ChsTeam;
import com.mlythg.helix.data.ChsEvent;
import com.mlythg.mabos.api.DynastyHelper;
import com.mlythg.mabos.api.interfaces.DynastyAPI;
import com.plnyyanks.tba.apiv2.APIv2Helper;
import com.plnyyanks.tba.apiv2.interfaces.APIv2;
import com.plnyyanks.tba.apiv2.models.Alliance;
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
//		List<Team> teams = api.fetchEventTeams(year + eventCode, null);
		List<Team> teams = api.fetchDistrictTeams(eventCode, year, null);
		PrintWriter writer;
		try {
			writer = new PrintWriter("prescout.xls", "UTF-8");
			writer.println("Team Number\tTeam Name\tRank Score");
			System.out.println("Team Number\tTeam Name\tRank Score");
			for (Team team : teams) {
				ChsTeam chsTeam = new ChsTeam();
				chsTeam.setNumber(team.getTeam_number());
				chsTeam.setName(team.getNickname().trim());
				chsTeam.setTeamKey(team.getKey());
				for (int i = 0; i < yearsToAverage; i++) {
					List<Event> events = api.fetchTeamEvents("frc" + team.getTeam_number(), year - i, null);
					for (Event event : events) {
						ChsEvent chsEvent = new ChsEvent();
						chsEvent.setYear(event.getYear());
						if (event.getEvent_type() > 1) {
							continue;
						}
						JsonArray rankings = api.fetchEventRankings(event.getYear() + event.getEvent_code(), null);
						if (rankings.size() > 0) {
							rankings.remove(0);
							for (JsonElement element : rankings) {
								if (element.getAsJsonArray().get(1).getAsString().equals("" + team.getTeam_number())) {
									chsEvent.setRankPercent(element.getAsJsonArray().get(0).getAsDouble() / rankings.size());
								}
							}
						}
						chsEvent.setPlayoffPosition(event.getPlayoffPosition(chsTeam));
						chsTeam.addEvent(chsEvent);
					}
				}
				System.out.println(chsTeam);
				writer.println(chsTeam);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
