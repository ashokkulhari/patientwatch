package com.reporting.entity;

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

@Entity
@Table(name = "child_pic", schema = "public", catalog = "reporting")
public class ChildPhoto {
	
	@Id
	@Column(name = "child_pic_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer childPicId;
	
	
	@Column(name = "child_pic_name")
	private String childPicName;
	
	@Column(name="obj_file")
	private byte[] imageByte;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_type_id")
	private Registration registration;

	public Integer getChildPicId() {
		return childPicId;
	}

	public void setChildPicId(Integer childPicId) {
		this.childPicId = childPicId;
	}

	public String getChildPicName() {
		return childPicName;
	}

	public void setChildPicName(String childPicName) {
		this.childPicName = childPicName;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
}
