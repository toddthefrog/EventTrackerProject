package com.skilldistillery.event.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "prescription_name")
	private String prescriptionName;
	
	@Column(name = "pill_size_in_ug")
	private int pillSizeInUg;
	
	@Column(name = "dose_in_ug")
	private int doseInUg;
	
	@Column(name = "times_daily")
	private int timesDaily;
	
	@Column(name = "is_active")
	private Boolean isActive;

	public Schedule() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPrescriptionName() {
		return prescriptionName;
	}

	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	public int getPillSizeInUg() {
		return pillSizeInUg;
	}

	public void setPillSizeInUg(int pillSizeInUg) {
		this.pillSizeInUg = pillSizeInUg;
	}

	public int getDoseInUg() {
		return doseInUg;
	}

	public void setDoseInUg(int doseInUg) {
		this.doseInUg = doseInUg;
	}

	public int getTimesDaily() {
		return timesDaily;
	}

	public void setTimesDaily(int timesDaily) {
		this.timesDaily = timesDaily;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", user=" + user + ", prescriptionName=" + prescriptionName + ", pillSizeInUg="
				+ pillSizeInUg + ", doseInUg=" + doseInUg + ", timesDaily=" + timesDaily + ", isActive=" + isActive
				+ "]";
	}
	
	

}
