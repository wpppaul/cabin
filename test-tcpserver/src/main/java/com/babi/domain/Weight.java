package com.babi.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Weight {

	private Integer weightId;
	
	private Float weight;
	
	private Float res;
	
	private Timestamp taken;
	
	private String cabinId;

	public Weight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getWeightId() {
		return weightId;
	}

	public void setWeightId(Integer weightId) {
		this.weightId = weightId;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getRes() {
		return res;
	}

	public void setRes(Float res) {
		this.res = res;
	}

	public String getTaken() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(taken);
	}

	public void setTaken(Timestamp taken) {
		this.taken = taken;
	}

	public String getCabinId() {
		return cabinId;
	}

	public void setCabinId(String cabinId) {
		this.cabinId = cabinId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cabinId == null) ? 0 : cabinId.hashCode());
		result = prime * result + ((res == null) ? 0 : res.hashCode());
		result = prime * result + ((taken == null) ? 0 : taken.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		result = prime * result + ((weightId == null) ? 0 : weightId.hashCode());
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
		Weight other = (Weight) obj;
		if (cabinId == null) {
			if (other.cabinId != null)
				return false;
		} else if (!cabinId.equals(other.cabinId))
			return false;
		if (res == null) {
			if (other.res != null)
				return false;
		} else if (!res.equals(other.res))
			return false;
		if (taken == null) {
			if (other.taken != null)
				return false;
		} else if (!taken.equals(other.taken))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		if (weightId == null) {
			if (other.weightId != null)
				return false;
		} else if (!weightId.equals(other.weightId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Weight [weightId=" + weightId + ", weight=" + weight + ", res=" + res + ", taken=" + taken
				+ ", cabinId=" + cabinId + "]";
	}
	
}
