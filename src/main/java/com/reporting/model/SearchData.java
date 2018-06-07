package com.reporting.model;

import java.io.Serializable;

public class SearchData implements Serializable{

	private static final long serialVersionUID = 1L;
	private String samId;
	private String childName;
	private String registrationDate;
	private String admissionDate;
	private String fatherName;
	private String mtcName;
	
	private String status;
	
//	public SearchData(String samId  , String childName,String registrationDate , 
//			String admissionDate,String fatherName ,String mtcName,String status){
//		this.samId=samId;
//		this.childName=childName;
//		this.registrationDate=registrationDate;
//		this.admissionDate=admissionDate;
//		this.fatherName=fatherName;
//		this.mtcName=mtcName;
//		this.status=status;
//	}
	
	public String getSamId() {
		return samId;
	}
	public void setSamId(String samId) {
		this.samId = samId;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMtcName() {
		return mtcName;
	}
	public void setMtcName(String mtcName) {
		this.mtcName = mtcName;
	}

}
