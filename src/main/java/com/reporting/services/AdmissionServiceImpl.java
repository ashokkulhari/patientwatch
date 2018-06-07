package com.reporting.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.dao.AdmissionDao;
import com.reporting.dao.AnthropometryDailyDao;
import com.reporting.entity.Admission;
import com.reporting.entity.AnthropometryDaily;

public class AdmissionServiceImpl implements AdmissionService {

	@Autowired
	private AdmissionDao admissionDao;
	
	@Autowired
	private AnthropometryDailyDao anthropometryDailyDao; 
	
	public AnthropometryDailyDao getAnthropometryDailyDao() {
		return anthropometryDailyDao;
	}

	public void setAnthropometryDailyDao(AnthropometryDailyDao anthropometryDailyDao) {
		this.anthropometryDailyDao = anthropometryDailyDao;
	}

	public AdmissionDao getAdmissionDao() {
		return admissionDao;
	}

	public void setAdmissionDao(AdmissionDao admissionDao) {
		this.admissionDao = admissionDao;
	}

	@Override
	public void saveAdmission(Admission admission) {
		admissionDao.saveAdmission(admission);
	}

	@Override
	public Admission findAdmissionById(int id) {
		return admissionDao.findAdmissionById(id);
	}

	@Override
	public void saveAnthropometryDaily(AnthropometryDaily anthropometryDaily) {
		anthropometryDailyDao.saveAnthropometryDaily(anthropometryDaily);
	}

	@Override
	public List<AnthropometryDaily> findAllForAdmission(int admissionId) {
		return anthropometryDailyDao.findAllForAdmission(admissionId);
	}
}
