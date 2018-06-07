package com.reporting.services;

import java.util.List;
import java.util.Map;

import com.reporting.entity.Block;
import com.reporting.entity.Country;
import com.reporting.entity.District;
import com.reporting.entity.MTC;
import com.reporting.entity.State;
import com.reporting.entity.Zone;

public interface LocationService {

	public void saveCountry(Country country);
	public List<Country> findAllCountries();
	
	public void saveState(State state);
	public List<State> findAllStates();
	
	public void saveZone(Zone zone);
	public List<Zone> findAllZones();
	
	public void saveDistrict(District district);
	public List<District> findAllDistricts();
	public List<String> findAllDistrictNames();
	public Map<String,List<String>> findAllLocationNames();
	
	public void saveBlock(Block block);
	public List<Block> findAllBlocks();
	public List<String> findAllBlockNames();
	
	public void saveMTC(MTC mtc);
	public List<MTC> findAllMTCs();
	public List<String> findAllMTCNames();
	public MTC findAllMTCByNames(String mtcName);
}
