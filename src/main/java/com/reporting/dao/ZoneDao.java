package com.reporting.dao;


import java.util.List;

import com.reporting.entity.Zone;

public interface ZoneDao {

	public void saveZone(Zone zone);
	public List<Zone> findAllZones();
}
