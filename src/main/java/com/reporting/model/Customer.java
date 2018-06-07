package com.reporting.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "customer")
public class Customer {

	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int c_id;
	
	@NotEmpty
	@Column
	private String c_name;
	
	@NotEmpty
	@Column
	private String c_password;

	
	// Getter Setter
	public int getc_id() {
		return c_id;
	}

	public void setc_id(int c_id) {
		this.c_id = c_id;
	}

	public String getc_name() {
		return c_name;
	}

	public void setc_name(String c_name) {
		this.c_name = c_name;
	}

	public String getc_password() {
		return c_password;
	}

	public void setc_password(String c_password) {
		this.c_password = c_password;
	}
	
}
