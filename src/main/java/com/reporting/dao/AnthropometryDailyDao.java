package com.reporting.dao;

import java.util.List;

import com.reporting.entity.AnthropometryDaily;

public interface AnthropometryDailyDao {
	
	public void saveAnthropometryDaily(AnthropometryDaily anthropometryDaily); 
	public List<AnthropometryDaily> findAllForAdmission(int admissionId);
}
