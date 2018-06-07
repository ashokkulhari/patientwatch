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
@Table(name = "anthropometry_one", schema = "public", catalog = "reporting")
public class AnthropometryOne {
	
	
	@Id
	@Column(name = "anthropometry_one_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer anthropometryOneId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_type_id")
	private Registration registration;
	
	@Column(name = "muac")
	private Double muac;
	
	@Column(name = "entry_type_id")
	private Integer entryTypeId;
	
	
	

	public Integer getEntryTypeId() {
		return entryTypeId;
	}

	public void setEntryTypeId(Integer entryTypeId) {
		this.entryTypeId = entryTypeId;
	}

	@Column(name = "len_or_height")
	private Double lenHeight;
	
	@Column(name = "weight")
	private Double weight;
	
	@Column(name = "wfh_wlh")
	private Integer wForHWForL;
	
	@Column(name = "oedema")
	private Boolean oedema;
	
	@Column(name = "medical_complications")
	private String medicalComplications;
	
	@Column(name = "remark")
	private String remark;

	public Integer getAnthropometryOneId() {
		return anthropometryOneId;
	}

	public void setAnthropometryOneId(Integer anthropometryOneId) {
		this.anthropometryOneId = anthropometryOneId;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Double getMuac() {
		return muac;
	}

	public void setMuac(Double muac) {
		this.muac = muac;
	}

	public Double getLenHeight() {
		return lenHeight;
	}

	public void setLenHeight(Double lenHeight) {
		this.lenHeight = lenHeight;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getwForHWForL() {
		return wForHWForL;
	}

	public void setwForHWForL(Integer wForHWForL) {
		this.wForHWForL = wForHWForL;
	}

	public Boolean getOedema() {
		return oedema;
	}

	public void setOedema(Boolean oedema) {
		this.oedema = oedema;
	}

	public String getMedicalComplications() {
		return medicalComplications;
	}

	public void setMedicalComplications(String medicalComplications) {
		this.medicalComplications = medicalComplications;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
