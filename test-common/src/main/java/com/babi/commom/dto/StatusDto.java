package com.babi.commom.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusDto {
	
	private String cabinid;
	
	private String door;
	
	private String cabinPM;
	
	private Integer temperature;
	
	private String light;
	
	private String color;
	
	private Integer emergencyStop;
	
	private Integer emergencyCall;	
	
	private Integer dur;
	
	private Integer dis;
	
	private Date due;
	
	private String speed;
	
	private String period;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private String time;

	public String getCabinid() {
		return cabinid;
	}

	public void setCabinid(String cabinid) {
		this.cabinid = cabinid;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getCabinPM() {
		return cabinPM;
	}

	public void setCabinPM(String cabinPM) {
		this.cabinPM = cabinPM;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public Integer getDur() {
		return dur;
	}

	public void setDur(Integer dur) {
		this.dur = dur;
	}

	public Integer getDis() {
		return dis;
	}

	public void setDis(Integer dis) {
		this.dis = dis;
	}

	public Date getDue() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-HH-mm");
//		return sdf.format(due);
		return due;
	}
	
	public String getMessageDue() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-HH-mm");
		return sdf.format(due);
	}
	public String getNormalDue() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(due);
	}

	public void setDue(String due) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			this.due = sdf.parse(due);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "StatusDto [cabinid=" + cabinid + ", door=" + door + ", cabinPM=" + cabinPM + ", temperature="
				+ temperature + ", light=" + light + ", color=" + color + ", emergencyStop=" + emergencyStop
				+ ", emergencyCall=" + emergencyCall + ", dur=" + dur + ", dis=" + dis + ", due=" + due + ", speed="
				+ speed + ", period=" + period + ", Time=" + time + "]";
	}	

}
