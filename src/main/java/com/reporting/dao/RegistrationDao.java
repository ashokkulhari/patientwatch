package com.reporting.dao;


import java.util.List;

import com.reporting.entity.Registration;

public interface RegistrationDao {

	public void saveRegistration(Registration registration); 
	public Registration findRegistrationById(int id); 
	public List<Object> findAllRegistrationViewData(String samId);
}
