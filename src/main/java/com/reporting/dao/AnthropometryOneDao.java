package com.reporting.dao;

import com.reporting.entity.AnthropometryOne;

public interface AnthropometryOneDao {

	public void saveAnthropometryOne(AnthropometryOne anthropometryOne); 
	public AnthropometryOne updateAnthropometryOne(AnthropometryOne anthropometryOne); 
	public AnthropometryOne findAnthropometryOneByRegId(int id); 
	public AnthropometryOne findAnthropometryOneByRegIdAndEntry(int registrationId , int entryType);
}
