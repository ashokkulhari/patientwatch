package com.reporting.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "admission", schema = "public", catalog = "reporting")
public class Admission {
	@Id
	@Column(name = "admission_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int admissionId;
	
	@Column(name = "sam_id")
	private String samId;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_type_id")
	private Registration registration;
	
	@Column(name = "admission_date")
	@Type(type="timestamp")
	private Date admissionDate;

	public int getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}

	public String getSamId() {
		return samId;
	}

	public void setSamId(String samId) {
		this.samId = samId;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

}
