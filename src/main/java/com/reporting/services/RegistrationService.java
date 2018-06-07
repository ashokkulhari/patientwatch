package com.reporting.services;

import java.util.List;

import com.reporting.entity.AnthropometryOne;
import com.reporting.entity.Child;
import com.reporting.entity.ChildPhoto;
import com.reporting.entity.Registration;

public interface RegistrationService {

	public void saveChild(Child child); 
	public Child updateChild(Child child); 
	public Child findChildById(int id); 
	public Child findChildByNameChild(String childName);
	public void saveRegistration(Registration registration); 
	public Registration findRegistrationById(int id); 
	public void saveChildPhoto(ChildPhoto childPhoto); 
	public ChildPhoto updateChildPhoto(ChildPhoto childPhto); 
	public ChildPhoto findChildPhotoByRegId(int id); 
	public void saveAnthropometryOne(AnthropometryOne anthropometryOne); 
	public AnthropometryOne updateAnthropometryOne(AnthropometryOne anthropometryOne); 
	public AnthropometryOne findAnthropometryOneByRegId(int id);
	public AnthropometryOne findAnthropometryOneByRegIdAndEntry(int registrationId , int entryType);
	public List<Object> findAllRegistrationViewData(String samId);
	
}
