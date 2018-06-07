package com.reporting.dao;


import java.util.List;
import java.util.Map;

import com.reporting.entity.District;

public interface DistrictDao {
	public void saveDistrict(District district);
	public List<District> findAllDistricts();
	public List<String> findAllDistrictNames();
	public Map<String,List<String>> findAllLocationNames();
}
