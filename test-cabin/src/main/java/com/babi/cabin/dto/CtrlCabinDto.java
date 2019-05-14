package com.babi.cabin.dto;

import javax.persistence.Column;

public class CtrlCabinDto {

	private String cabinId;

	private Float temperatue;

	private String light;

	private String door;

	private String ventilation;

	private String acmode;

	private String color;

	private String fanMode;

	private Integer emergencyStop;

	private Integer emergencyCall;

	private String cabinPM;

	public String getCabinId() {
		return cabinId;
	}

	public void setCabinId(String cabinId) {
		this.cabinId = cabinId;
	}

	public void setTemperatue(Float temperatue) {
		this.temperatue = temperatue;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getVentilation() {
		return ventilation;
	}

	public void setVentilation(String ventilation) {
		this.ventilation = ventilation;
	}

	public String getAcmode() {
		return acmode;
	}

	public void setAcmode(String acmode) {
		this.acmode = acmode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFanMode() {
		return fanMode;
	}

	public void setFanMode(String fanMode) {
		this.fanMode = fanMode;
	}

	public Integer getEmergencyStop() {
		return emergencyStop;
	}

	public void setEmergencyStop(Integer emergencyStop) {
		this.emergencyStop = emergencyStop;
	}

	public Integer getEmergencyCall() {
		return emergencyCall;
	}

	public void setEmergencyCall(Integer emergencyCall) {
		this.emergencyCall = emergencyCall;
	}

	public String getCabinPM() {
		return cabinPM;
	}

	public void setCabinPM(String cabinPM) {
		this.cabinPM = cabinPM;
	}

	public Float getTemperatue() {
		return temperatue;
	}

}
