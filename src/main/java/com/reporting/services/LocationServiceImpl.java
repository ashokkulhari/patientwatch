package com.reporting.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.reporting.dao.BlockDao;
import com.reporting.dao.CountryDao;
import com.reporting.dao.DistrictDao;
import com.reporting.dao.MTCDao;
import com.reporting.dao.StateDao;
import com.reporting.dao.ZoneDao;
import com.reporting.entity.Block;
import com.reporting.entity.Country;
import com.reporting.entity.District;
import com.reporting.entity.MTC;
import com.reporting.entity.State;
import com.reporting.entity.Zone;

public class LocationServiceImpl  implements LocationService{

	@Autowired
	private CountryDao countryDao;
	@Autowired
	private StateDao stateDao;
	@Autowired
	private ZoneDao zoneDao;
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private BlockDao blockDao;
	@Autowired
	private MTCDao mtcDao;
	


	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public void setStateDao(StateDao stateDao) {
		this.stateDao = stateDao;
	}

	public void setZoneDao(ZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}

	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	public void setBlockDao(BlockDao blockDao) {
		this.blockDao = blockDao;
	}

	public void setMtcDao(MTCDao mtcDao) {
		this.mtcDao = mtcDao;
	}

	@Transactional
	public void saveCountry(Country country) {
		System.out.println("..........save country..");
	}

	@Transactional
	public List<Country> findAllCountries() {
		return countryDao.findAllCountries();
	}

	@Transactional
	public void saveState(State state) {
	}

	@Transactional
	public List<State> findAllStates() {
		return stateDao.findAllStates();
	}

	@Transactional
	public void saveZone(Zone zone) {
	}

	@Transactional
	public List<Zone> findAllZones() {
		return zoneDao.findAllZones();
	}

	@Transactional
	public void saveDistrict(District district) {
	}

	@Transactional
	public List<District> findAllDistricts() {
		return districtDao.findAllDistricts();
	}

	@Transactional
	public void saveMTC(MTC mtc) {
	}

	@Transactional
	public List<MTC> findAllMTCs() {
		return mtcDao.findAllMTCs();
	}

	@Transactional
	public void saveBlock(Block block) {
		
	}

	@Transactional
	public List<Block> findAllBlocks() {
		return blockDao.findAllBlocks();
	}

	@Override
	public List<String> findAllDistrictNames() {
		return districtDao.findAllDistrictNames();
	}

	@Override
	public List<String> findAllBlockNames() {
		return blockDao.findAllBlockNames();
	}

	@Override
	public List<String> findAllMTCNames() {
		return mtcDao.findAllMTCNames();
	}

	@Override
	public Map<String, List<String>> findAllLocationNames() {
		return districtDao.findAllLocationNames();
	}

	@Override
	public MTC findAllMTCByNames(String mtcName) {
		return mtcDao.findAllMTCByNames(mtcName);
	}
	

}
