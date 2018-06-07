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
@Table(name = "mtc")
public class MTC {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mtc_id;
	
	
	@Column
	private String mtc_name;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "block_type_id")
	private Block block;

	public int getMtc_id() {
		return mtc_id;
	}

	public void setMtc_id(int mtc_id) {
		this.mtc_id = mtc_id;
	}

	public String getMtc_name() {
		return mtc_name;
	}

	public void setMtc_name(String mtc_name) {
		this.mtc_name = mtc_name;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
}
