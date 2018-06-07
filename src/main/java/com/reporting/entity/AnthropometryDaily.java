package com.reporting.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "anthropometry_daily", schema = "public", catalog = "reporting")
public class AnthropometryDaily {
	
	@Id
	@Column(name = "anthropometry_daily_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer anthropometryDailyId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admission_type_id")
	private Admission admission;
	
	@Column(name = "day")
	private String day;
	@Column(name = "day_date")
	@Type(type="timestamp")
	private Date dayDate;
	@Column(name = "muac")
	private Double muac;
	@Column(name = "weight")
	private Double weight;

	@Column(name = "weight_gain")
	private Double weightGain;
	
	@Column(name = "height")
	private Double height;
	@Column(name = "zscore")
	private String zScore;
	@Column(name = "oedema")
	private String oedema;
	
	@Column(name = "no_feeds")
	private Integer noFeeds;
	@Column(name = "vol_taken")
	private Double volTaken;
	public Integer getAnthropometryDailyId() {
		return anthropometryDailyId;
	}
	public void setAnthropometryDailyId(Integer anthropometryDailyId) {
		this.anthropometryDailyId = anthropometryDailyId;
	}
	public Admission getAdmission() {
		return admission;
	}
	public void setAdmission(Admission admission) {
		this.admission = admission;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Date getDayDate() {
		return dayDate;
	}
	public void setDayDate(Date dayDate) {
		this.dayDate = dayDate;
	}
	public Double getMuac() {
		return muac;
	}
	public void setMuac(Double muac) {
		this.muac = muac;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getWeightGain() {
		return weightGain;
	}
	public void setWeightGain(Double weightGain) {
		this.weightGain = weightGain;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public String getzScore() {
		return zScore;
	}
	public void setzScore(String zScore) {
		this.zScore = zScore;
	}
	public String getOedema() {
		return oedema;
	}
	public void setOedema(String oedema) {
		this.oedema = oedema;
	}
	public Integer getNoFeeds() {
		return noFeeds;
	}
	public void setNoFeeds(Integer noFeeds) {
		this.noFeeds = noFeeds;
	}
	public Double getVolTaken() {
		return volTaken;
	}
	public void setVolTaken(Double volTaken) {
		this.volTaken = volTaken;
	}
}