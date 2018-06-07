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
@Table(name = "block")
public class Block {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int block_id;
	
	
	@Column
	private String block_name;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "district_type_id")
	private District district;

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public int getBlock_id() {
		return block_id;
	}

	public void setBlock_id(int block_id) {
		this.block_id = block_id;
	}

	public String getBlock_name() {
		return block_name;
	}

	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}

	

}
