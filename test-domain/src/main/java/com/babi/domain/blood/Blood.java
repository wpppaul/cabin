package com.babi.domain.blood;

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
@Table(name = "bloods")
public class Blood {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bloodId;
	
	@Column(name="systolic")
	private Integer systolic;
	
	@Column(name="diastolic")
	private Integer diastolic;
	
	@Column(name="pulse")
	private Integer pulse;
	
	@Column(name="taken")
	private Timestamp taken;
	
	@Column(name="cabin_id")
	private String cabinId;

	public Blood() {
		super();
	}

	public Integer getBloodId() {
		return bloodId;
	}

	public void setBloodId(Integer bloodId) {
		this.bloodId = bloodId;
	}

	public Integer getSystolic() {
		return systolic;
	}

	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}

	public Integer getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(Integer diastolic) {
		this.diastolic = diastolic;
	}

	public Integer getPulse() {
		return pulse;
	}

	public void setPulse(Integer pulse) {
		this.pulse = pulse;
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
		result = prime * result + ((bloodId == null) ? 0 : bloodId.hashCode());
		result = prime * result + ((cabinId == null) ? 0 : cabinId.hashCode());
		result = prime * result + ((diastolic == null) ? 0 : diastolic.hashCode());
		result = prime * result + ((pulse == null) ? 0 : pulse.hashCode());
		result = prime * result + ((systolic == null) ? 0 : systolic.hashCode());
		result = prime * result + ((taken == null) ? 0 : taken.hashCode());
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
		Blood other = (Blood) obj;
		if (bloodId == null) {
			if (other.bloodId != null)
				return false;
		} else if (!bloodId.equals(other.bloodId))
			return false;
		if (cabinId == null) {
			if (other.cabinId != null)
				return false;
		} else if (!cabinId.equals(other.cabinId))
			return false;
		if (diastolic == null) {
			if (other.diastolic != null)
				return false;
		} else if (!diastolic.equals(other.diastolic))
			return false;
		if (pulse == null) {
			if (other.pulse != null)
				return false;
		} else if (!pulse.equals(other.pulse))
			return false;
		if (systolic == null) {
			if (other.systolic != null)
				return false;
		} else if (!systolic.equals(other.systolic))
			return false;
		if (taken == null) {
			if (other.taken != null)
				return false;
		} else if (!taken.equals(other.taken))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Blood [bloodId=" + bloodId + ", systolic=" + systolic + ", diastolic=" + diastolic + ", pulse=" + pulse
				+ ", taken=" + taken + ", cabinId=" + cabinId + "]";
	}

	

}
