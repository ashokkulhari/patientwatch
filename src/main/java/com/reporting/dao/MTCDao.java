package com.reporting.dao;


import java.util.List;

import com.reporting.entity.MTC;

public interface MTCDao {

	public void saveMTC(MTC mtc);
	public List<MTC> findAllMTCs();
	public List<String> findAllMTCNames();
	public MTC findAllMTCByNames(String mtcName);
}
