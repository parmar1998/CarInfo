package com.rp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CricketMatch {
	private String id;
	private String matchType;
	private String status;
	private String t1; // Team 1
	private String t2; // Team 2
	private String t1s; // Team 1 Score
	private String t2s; // Team 2 Score

	@JsonProperty("t1img")
	private String t1Image;

	@JsonProperty("t2img")
	private String t2Image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getT1s() {
		return t1s;
	}

	public void setT1s(String t1s) {
		this.t1s = t1s;
	}

	public String getT2s() {
		return t2s;
	}

	public void setT2s(String t2s) {
		this.t2s = t2s;
	}

	public String getT1Image() {
		return t1Image;
	}

	public void setT1Image(String t1Image) {
		this.t1Image = t1Image;
	}

	public String getT2Image() {
		return t2Image;
	}

	public void setT2Image(String t2Image) {
		this.t2Image = t2Image;
	}

}
