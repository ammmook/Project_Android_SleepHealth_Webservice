package com.springmvc.bean;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sleeps")
public class Sleep {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sleepId;

	@Column(name = "username", length = 20, nullable = false)
	private String username;

	@Column(name = "sleep_date", nullable = false)
	private String date;

	@Column(name = "sleep_bed", nullable = false)
	private String bedTime;

	@Column(name = "sleep_wakeup", nullable = false)
	private String wakeUpTime;

	@Column(name = "sleep_duration", nullable = false)
	private int duration;

	@Column(name = "sleep_quality", nullable = false)
	private int quality;

	@Column(name = "sleep_debt", nullable = false)
	private int debt;

	public Sleep() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sleep(int sleepId, String username, String date, String bedTime, String wakeUpTime, int duration,
			int quality, int debt) {
		super();
		this.sleepId = sleepId;
		this.username = username;
		this.date = date;
		this.bedTime = bedTime;
		this.wakeUpTime = wakeUpTime;
		this.duration = duration;
		this.quality = quality;
		this.debt = debt;
	}

	public int getSleepId() {
		return sleepId;
	}

	public void setSleepId(int sleepId) {
		this.sleepId = sleepId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBedTime() {
		return bedTime;
	}

	public void setBedTime(String bedTime) {
		this.bedTime = bedTime;
	}

	public String getWakeUpTime() {
		return wakeUpTime;
	}

	public void setWakeUpTime(String wakeUpTime) {
		this.wakeUpTime = wakeUpTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getDebt() {
		return debt;
	}

	public void setDebt(int debt) {
		this.debt = debt;
	}

}
