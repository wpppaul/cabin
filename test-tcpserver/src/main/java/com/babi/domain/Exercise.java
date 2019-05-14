package com.babi.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Exercise {

	private Integer exerciseId;
	
	private String cabinId;
	
	private Integer treadmillId;
	
	private Timestamp start;
	
	private Timestamp due;
	
	private Float distance;
	
	private Float calorie;
	
	private Float duration;
	
	private Float maxspeed;

	public Exercise() {
		super();
	}

	public Exercise(Integer exerciseId, String  cabinId, Integer treadmillId, Timestamp start, Timestamp due,
			Float distance, Float calorie, Float duration,Float maxspeed) {
		super();
		this.exerciseId = exerciseId;
		this.cabinId = cabinId;
		this.treadmillId = treadmillId;
		this.start = start;
		this.due = due;
		this.distance = distance;
		this.calorie = calorie;
		this.duration = duration;
		this.maxspeed = maxspeed;
	}

	public Integer getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getCabinId() {
		return cabinId;
	}

	public void setCabinId(String cabinId) {
		this.cabinId = cabinId;
	}

	public Integer getTreadmillId() {
		return treadmillId;
	}

	public void setTreadmillId(Integer treadmillId) {
		this.treadmillId = treadmillId;
	}

	public String getStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(start);
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public String getDue() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(due);
	}

	public void setDue(Timestamp due) {
		this.due = due;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Float getCalorie() {
		return calorie;
	}

	public void setCalorie(Float calorie) {
		this.calorie = calorie;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public Float getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Float maxspeed) {
		this.maxspeed = maxspeed;
	}

	@Override
	public String toString() {
		return "Exercise [exerciseId=" + exerciseId + ", cabinId=" + cabinId + ", treadmillId=" + treadmillId
				+ ", start=" + start + ", due=" + due + ", distance=" + distance + ", calorie=" + calorie
				+ ", duration=" + duration + ", maxspeed=" + maxspeed + "]";
	}

	

}
