package com.plnyyanks.tba.apiv2.models;

import java.util.List;

public class Alliance {
	
	private List<String> declines;
	private String name;
	private List<String> picks;
	
	public List<String> getDeclines() {
		return declines;
	}
	
	public void setDeclines(List<String> declines) {
		this.declines = declines;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getPicks() {
		return picks;
	}
	
	public void setPicks(List<String> picks) {
		this.picks = picks;
	}
}
