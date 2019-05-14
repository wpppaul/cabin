package com.babi.domain.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name = "t_status")
public class Status {
	
	@Id
	@Column(name="cabinid")
	private String cabinid;
	
	@Column(name="door")
	private String door;
	
	@Column(name="cabinPM")
	private String cabinPM;
	
	@Column(name="temperature")
	private Integer temperature;
	
	@Column(name="light")
	private String light;
	
	@Column(name="color")
	private String color;
	
	@Column(name="emergency_stop")
	private Integer emergencyStop;
	
	@Column(name="emergency_call")
	private Integer emergencyCall;	
	
	@Column(name="dur")
	private Integer dur;
	
	@Column(name="dis")
	private Integer dis;
	
	@Column(name="cal")
	private String cal;
	
	@Column(name="due")
	private Date due;
	
	@Column(name="res")
	private String res;
	
	@Column(name="weight")
	private String weight;
	
	@Column(name="speed")
	private String speed;
	
	@Column(name="period")
	private String period;
	

	@Column(name="hiBlood")
	private String hiBlood;
	
	@Column(name="loBlood")
	private String loBlood;
	
	@Column(name="pulse")
	private String pulse;
	
	@Column(name="measureDate")
	private String measureDate;
		
	public Date getDue() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-HH-mm");
//		return sdf.format(due);
		return due;
	}
	
	public String getNormalDue() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(due);
	}

	public void setDue(Date due) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//		try {
//			this.due = sdf.parse(due);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		this.due=due;
	}
	
	public Date getWxDue() {
		return due;
	}

	public Integer getDur() {
		return dur;
	}

	public void setDur(Integer dur) {
		this.dur = dur;
	}

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


	public Integer getDis() {
		return dis;
	}

	public void setDis(Integer dis) {
		this.dis = dis;
	}

	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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

	public String getHiBlood() {
		return hiBlood;
	}

	public void setHiBlood(String hiBlood) {
		this.hiBlood = hiBlood;
	}

	public String getLoBlood() {
		return loBlood;
	}

	public void setLoBlood(String loBlood) {
		this.loBlood = loBlood;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getMeasureDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf1.format(sdf.parse(measureDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setMeasureDate(String measureDate) {
		this.measureDate = measureDate;
	}

	@Override
	public String toString() {
		return "Status [cabinid=" + cabinid + ", door=" + door + ", cabinPM=" + cabinPM + ", temperature=" + temperature
				+ ", light=" + light + ", color=" + color + ", emergencyStop=" + emergencyStop + ", emergencyCall="
				+ emergencyCall + ", dur=" + dur + ", dis=" + dis + ", cal=" + cal + ", due=" + due + ", res=" + res
				+ ", weight=" + weight + ", speed=" + speed + ", period=" + period + ", hiBlood=" + hiBlood
				+ ", loBlood=" + loBlood + ", pulse=" + pulse + ", measureDate=" + measureDate + "]";
	}
	

}
