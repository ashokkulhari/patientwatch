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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "registration", schema = "public", catalog = "reporting")
public class Registration {

	@Id
	@Column(name = "registration_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int registrationId;
	
	
	@Column(name = "annual_no")
	private Integer annualNo;
	@Column(name = "investigator_name")
	private String investigatorName;
	@Column(name = "visit_date")
	@Type(type="timestamp")
	private Date visitDate;
	
	@Column(name = "registration_date")
	@Type(type="timestamp")
	private Date registrationDate;
	@Column(name = "referred_by")
	private String referredBy;
	@Column(name = "referred_by_details")
	private String referredByDetails;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_type_id")
	private Child child;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mtc_type_id")
	private MTC mtc;

	
	
	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public Integer getAnnualNo() {
		return annualNo;
	}

	public void setAnnualNo(Integer annualNo) {
		this.annualNo = annualNo;
	}

	public String getInvestigatorName() {
		return investigatorName;
	}

	public void setInvestigatorName(String investigatorName) {
		this.investigatorName = investigatorName;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getReferredByDetails() {
		return referredByDetails;
	}

	public void setReferredByDetails(String referredByDetails) {
		this.referredByDetails = referredByDetails;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public MTC getMtc() {
		return mtc;
	}

	public void setMtc(MTC mtc) {
		this.mtc = mtc;
	}

	
	
}
