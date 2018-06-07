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
@Table(name = "state")
public class State {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int state_id;
	
	
	@Column
	private String state_name;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_type_id")
	private Country country;
	
	
	
	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public int getState_id() {
		return state_id;
	}


	public void setState_id(int state_id) {
		this.state_id = state_id;
	}


	public String getState_name() {
		return state_name;
	}


	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
}
