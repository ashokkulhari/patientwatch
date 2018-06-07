package com.reporting.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "child", schema = "public", catalog = "reporting")
public class Child {

	
	@Id
	@Column(name = "child_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer childId;
	
	@Column(name = "child_name")
	private String childName;
	@Column(name = "father_name")
	private String fatherName;
	@Column(name = "mother_name")
	private String motherName;
	@Column(name = "dob")
	private Date dateOfBirth;
	@Column(name = "age_years")
	private Integer ageYears;
	@Column(name = "age_months")
	private Integer ageMonths;
	@Column(name = "gender")
	private String gender;
	@Column(name = "caste")
	private String caste;
	@Column(name = "current_address")
	private String currentAddress;
	@Column(name = "permanent_address")
	private String permanentAddress;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "aadhar_number")
	private String aadharNumber;
	@Column(name = "bhamasha_number")
	private String bhamashaNumber;
	public Integer getChildId() {
		return childId;
	}
	public void setChildId(Integer childId) {
		this.childId = childId;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Integer getAgeYears() {
		return ageYears;
	}
	public void setAgeYears(Integer ageYears) {
		this.ageYears = ageYears;
	}
	public Integer getAgeMonths() {
		return ageMonths;
	}
	public void setAgeMonths(Integer ageMonths) {
		this.ageMonths = ageMonths;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getBhamashaNumber() {
		return bhamashaNumber;
	}
	public void setBhamashaNumber(String bhamashaNumber) {
		this.bhamashaNumber = bhamashaNumber;
	}
	  
}
