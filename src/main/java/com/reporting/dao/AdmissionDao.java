package com.reporting.dao;

import com.reporting.entity.Admission;

public interface AdmissionDao {

	public void saveAdmission(Admission admission); 
	public Admission findAdmissionById(int id); 
}
