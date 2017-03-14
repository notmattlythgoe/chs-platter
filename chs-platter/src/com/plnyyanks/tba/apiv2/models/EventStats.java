package com.plnyyanks.tba.apiv2.models;

import java.util.HashMap;
import java.util.Map;

public class EventStats {
	private Map<String, Double> oprs = new HashMap<String, Double>();

	public Map<String, Double> getOprs() {
		return oprs;
	}
	
	public void setOprs(Map<String, Double> oprs) {
		this.oprs = oprs;;
	}
	
	public double getOpr(int teamNumber) {
		if (oprs == null || oprs.get("" + teamNumber) == null) {
			return 0;
		}
		return oprs.get("" + teamNumber);
	}
}
