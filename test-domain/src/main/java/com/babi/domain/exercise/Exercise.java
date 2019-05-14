package com.babi.domain.exercise;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "exercises")
public class Exercise {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer exerciseId;
	
	@Column(name="cabin_id")
	private String cabinId;
	
	@Column(name="treadmill_id")
	private Integer treadmillId;
	
	@Column(name="start")
	private Timestamp start;
	
	@Column(name="due")
	private Timestamp due;
	
	@Column(name="distance")
	private Float distance;
	
	@Column(name="calorie")
	private Float calorie;
	
	@Column(name="duration")
	private Float duration;
	
	@Column(name="maxspd")
	private Float maxspd;

	public Exercise() {
		super();
	}

	public Exercise(Integer exerciseId, String  cabinId, Integer treadmillId, Timestamp start, Timestamp due,
			Float distance, Float calorie, Float duration, Float maxspd) {
		super();
		this.exerciseId = exerciseId;
		this.cabinId = cabinId;
		this.treadmillId = treadmillId;
		this.start = start;
		this.due = due;
		this.distance = distance;
		this.calorie = calorie;
		this.duration = duration;
		this.maxspd = maxspd;
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

	public Float getMaxspd() {
		return maxspd;
	}

	public void setMaxspd(Float maxspd) {
		this.maxspd = maxspd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cabinId == null) ? 0 : cabinId.hashCode());
		result = prime * result + ((calorie == null) ? 0 : calorie.hashCode());
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((due == null) ? 0 : due.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((exerciseId == null) ? 0 : exerciseId.hashCode());
		result = prime * result + ((maxspd == null) ? 0 : maxspd.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((treadmillId == null) ? 0 : treadmillId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		if (cabinId == null) {
			if (other.cabinId != null)
				return false;
		} else if (!cabinId.equals(other.cabinId))
			return false;
		if (calorie == null) {
			if (other.calorie != null)
				return false;
		} else if (!calorie.equals(other.calorie))
			return false;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (due == null) {
			if (other.due != null)
				return false;
		} else if (!due.equals(other.due))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (exerciseId == null) {
			if (other.exerciseId != null)
				return false;
		} else if (!exerciseId.equals(other.exerciseId))
			return false;
		if (maxspd == null) {
			if (other.maxspd != null)
				return false;
		} else if (!maxspd.equals(other.maxspd))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (treadmillId == null) {
			if (other.treadmillId != null)
				return false;
		} else if (!treadmillId.equals(other.treadmillId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Exercise [exerciseId=" + exerciseId + ", cabinId=" + cabinId + ", treadmillId=" + treadmillId
				+ ", start=" + start + ", due=" + due + ", distance=" + distance + ", calorie=" + calorie
				+ ", duration=" + duration + ", maxspd=" + maxspd + "]";
	}

}
