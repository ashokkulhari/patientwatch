package com.reporting.dao;

import java.util.List;


import com.reporting.entity.Country;

public interface CountryDao {
	public void saveCountry(Country country);
	public List<Country> findAllCountries();
}
