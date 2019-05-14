package com.babi.lockservice;

/**
 * 
 * @author	LiWenhui
 * @Date	2018.03.19
 * @author	GuoLiangyun
 * @Date	2018.03.23
 */
public class CarportLock {
	private String CabinID; 		// Cabin ID
	private int Door;			// Door Status
	private int AQI;			// PM2.5
	private int temperature;	// Cabin Temperature
	private int presetTemp;		// Preset Temperature
	private int light;			// Light
	private int color;			// Color Light
	private int emgStop;		// Emergency Stop
	private int emgCall;		// Emergency Call
	private int duration;		// Run Duration
	private int distance;		// Distance
	private int calorie;
	
	public String getCabinID() {
		return CabinID;
	}
	public void setCabinID(String cabinID) {
		CabinID = cabinID;
	}
	public int getDoor() {
		return Door;
	}
	public void setDoor(int door) {
		Door = door;
	}
	public int getAQI() {
		return AQI;
	}
	public void setAQI(int aQI) {
		AQI = aQI;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getPresetTemp() {
		return presetTemp;
	}
	public void setPresetTemp(int presetTemp) {
		this.presetTemp = presetTemp;
	}
	public int getLight() {
		return light;
	}
	public void setLight(int light) {
		this.light = light;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getEmgStop() {
		return emgStop;
	}
	public void setEmgStop(int emgStop) {
		this.emgStop = emgStop;
	}
	public int getEmgCall() {
		return emgCall;
	}
	public void setEmgCall(int emgCall) {
		this.emgCall = emgCall;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}

	

}
