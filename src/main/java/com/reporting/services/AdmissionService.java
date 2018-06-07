package com.reporting.services;

import java.util.List;

import com.reporting.entity.Admission;
import com.reporting.entity.AnthropometryDaily;

public interface AdmissionService {

	public void saveAdmission(Admission admission); 
	public Admission findAdmissionById(int id);
	public void saveAnthropometryDaily(AnthropometryDaily anthropometryDaily); 
	public List<AnthropometryDaily> findAllForAdmission(int admissionId);
}
