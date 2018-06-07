package com.reporting.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.dao.AnthropometryDailyDao;
import com.reporting.dao.AnthropometryOneDao;
import com.reporting.dao.ChildDao;
import com.reporting.dao.ChildPhotoDao;
import com.reporting.dao.RegistrationDao;
import com.reporting.entity.AnthropometryDaily;
import com.reporting.entity.AnthropometryOne;
import com.reporting.entity.Child;
import com.reporting.entity.ChildPhoto;
import com.reporting.entity.Registration;

public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private ChildDao childDao;
	@Autowired
	private ChildPhotoDao childPhotoDao;
	@Autowired
	private AnthropometryOneDao anthropometryOneDao; 
	

	public AnthropometryOneDao getAnthropometryOneDao() {
		return anthropometryOneDao;
	}

	public void setAnthropometryOneDao(AnthropometryOneDao anthropometryOneDao) {
		this.anthropometryOneDao = anthropometryOneDao;
	}

	public ChildPhotoDao getChildPhotoDao() {
		return childPhotoDao;
	}

	public void setChildPhotoDao(ChildPhotoDao childPhotoDao) {
		this.childPhotoDao = childPhotoDao;
	}

	public RegistrationDao getRegistrationDao() {
		return registrationDao;
	}

	public void setRegistrationDao(RegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}

	public ChildDao getChildDao() {
		return childDao;
	}

	public void setChildDao(ChildDao childDao) {
		this.childDao = childDao;
	}

	@Override
	public void saveChild(Child child) {
		childDao.saveChild(child);
	}

	@Override
	public Child updateChild(Child child) {
		return childDao.updateChild(child);
	}

	@Override
	public Child findChildById(int id) {
		return childDao.findChildById(id);
	}

	@Override
	public Child findChildByNameChild(String childName) {
		return childDao.findChildByNameChild(childName);
	}

	@Override
	public void saveRegistration(Registration registration) {
		registrationDao.saveRegistration(registration);
	}

	@Override
	public Registration findRegistrationById(int id) {
		return registrationDao.findRegistrationById(id);
	}

	@Override
	public void saveChildPhoto(ChildPhoto childPhoto) {
		childPhotoDao.saveChildPhoto(childPhoto);
		
	}

	@Override
	public ChildPhoto updateChildPhoto(ChildPhoto childPhto) {
		return childPhotoDao.updateChildPhoto(childPhto);
	}

	@Override
	public ChildPhoto findChildPhotoByRegId(int id) {
		return childPhotoDao.findChildPhotoByRegId(id);
	}

	@Override
	public void saveAnthropometryOne(AnthropometryOne anthropometryOne) {
		anthropometryOneDao.saveAnthropometryOne(anthropometryOne);
	}

	@Override
	public AnthropometryOne updateAnthropometryOne(AnthropometryOne anthropometryOne) {
		return anthropometryOneDao.updateAnthropometryOne(anthropometryOne);
	}

	@Override
	public AnthropometryOne findAnthropometryOneByRegId(int id) {
		return anthropometryOneDao.findAnthropometryOneByRegId(id);
	}

	@Override
	public AnthropometryOne findAnthropometryOneByRegIdAndEntry(int registrationId, int entryType) {
		return anthropometryOneDao.findAnthropometryOneByRegIdAndEntry(registrationId, entryType);
	}

	@Override
	public List<Object> findAllRegistrationViewData(String samId) {
		return registrationDao.findAllRegistrationViewData(samId);
	}

	

}
