package com.utk.helloweather;

public class Weather {
	private String date;
	private String maxC;
	private String minC;
	private String descritpion;
	private String thumbnail;
	public Weather(String date, String maxC, String minC, String descritpion,
			String thumbnail) {
		super();
		this.date = date;
		this.maxC = maxC;
		this.minC = minC;
		this.descritpion = descritpion;
		this.thumbnail = thumbnail;
	}
	public String getDate() {
		return date;
	}
	public String getMaxC() {
		return maxC;
	}
	public String getMinC() {
		return minC;
	}
	public String getDescritpion() {
		return descritpion;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	
}
