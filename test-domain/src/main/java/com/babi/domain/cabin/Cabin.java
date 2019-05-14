package com.babi.domain.cabin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "cabins")
public class Cabin {
	
	@Id
	@Column(name ="id")
	private String cabinId;
	
	@Column(name ="door")
	private Integer door;
	
	@Column(name ="light")
	private Integer light;
	
	@Column(name ="color")
	private Integer color;
	
	@Column(name ="aqi")
	private Integer aqi;
	
	@Column(name ="temperature")
	private Float temperature;
	
	@Column(name ="emergency_stop")
	private Integer emergencyStop;
	
	@Column(name ="emergency_call")
	private Integer emergencyCall;
	
	@Column(name ="location")
	private String location;
	
	@Column(name ="online")
	private String online;

	public Cabin() {
		super();
	}

	public Cabin(String cabinId, Integer door, Integer light, Integer color, Integer aqi, Float temperature,
			Integer emergencyStop, Integer emergencyCall, String location, String online) {
		super();
		this.cabinId = cabinId;
		this.door = door;
		this.light = light;
		this.color = color;
		this.aqi = aqi;
		this.temperature = temperature;
		this.emergencyStop = emergencyStop;
		this.emergencyCall = emergencyCall;
		this.location = location;
		this.online = online;
	}

	public String getCabinId() {
		return cabinId;
	}

	public void setCabinId(String cabinId) {
		this.cabinId = cabinId;
	}

	public Integer getDoor() {
		return door;
	}

	public void setDoor(Integer door) {
		this.door = door;
	}

	public Integer getLight() {
		return light;
	}

	public void setLight(Integer light) {
		this.light = light;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getAqi() {
		return aqi;
	}

	public void setAqi(Integer aqi) {
		this.aqi = aqi;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aqi == null) ? 0 : aqi.hashCode());
		result = prime * result + ((cabinId == null) ? 0 : cabinId.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((door == null) ? 0 : door.hashCode());
		result = prime * result + ((emergencyCall == null) ? 0 : emergencyCall.hashCode());
		result = prime * result + ((emergencyStop == null) ? 0 : emergencyStop.hashCode());
		result = prime * result + ((light == null) ? 0 : light.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((online == null) ? 0 : online.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
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
		Cabin other = (Cabin) obj;
		if (aqi == null) {
			if (other.aqi != null)
				return false;
		} else if (!aqi.equals(other.aqi))
			return false;
		if (cabinId == null) {
			if (other.cabinId != null)
				return false;
		} else if (!cabinId.equals(other.cabinId))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (door == null) {
			if (other.door != null)
				return false;
		} else if (!door.equals(other.door))
			return false;
		if (emergencyCall == null) {
			if (other.emergencyCall != null)
				return false;
		} else if (!emergencyCall.equals(other.emergencyCall))
			return false;
		if (emergencyStop == null) {
			if (other.emergencyStop != null)
				return false;
		} else if (!emergencyStop.equals(other.emergencyStop))
			return false;
		if (light == null) {
			if (other.light != null)
				return false;
		} else if (!light.equals(other.light))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (online == null) {
			if (other.online != null)
				return false;
		} else if (!online.equals(other.online))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cabin [cabinId=" + cabinId + ", door=" + door + ", light=" + light + ", color=" + color + ", aqi=" + aqi
				+ ", temperature=" + temperature + ", emergencyStop=" + emergencyStop + ", emergencyCall="
				+ emergencyCall + ", location=" + location + ", online=" + online + "]";
	}
	
}
