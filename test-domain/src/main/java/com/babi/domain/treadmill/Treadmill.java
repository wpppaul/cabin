package com.babi.domain.treadmill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name = "treadmills")
public class Treadmill {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer treadmillId;
	
	private String model;
	
	private String serial;
	
	private String manufacturer;
	
	public Treadmill() {
		super();
	}

	public Treadmill(Integer treadmillId, String model, String serial, String manufacturer) {
		super();
		this.treadmillId = treadmillId;
		this.model = model;
		this.serial = serial;
		this.manufacturer = manufacturer;
	}

	public Integer getTreadmillId() {
		return treadmillId;
	}

	public void setTreadmillId(Integer treadmillId) {
		this.treadmillId = treadmillId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
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
		Treadmill other = (Treadmill) obj;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
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
		return "Treadmill [treadmillId=" + treadmillId + ", model=" + model + ", serial=" + serial + ", manufacturer="
				+ manufacturer + "]";
	}

}
