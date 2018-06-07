package com.reporting.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "zone")
public class Zone {

	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int zone_id;
	
	
	@Column
	private String zone_name;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_type_id")
	private State state;

	public int getZone_id() {
		return zone_id;
	}

	public void setZone_id(int zone_id) {
		this.zone_id = zone_id;
	}

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
