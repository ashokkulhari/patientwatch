package com.reporting.dao;

import com.reporting.entity.ChildPhoto;

public interface ChildPhotoDao {

	public void saveChildPhoto(ChildPhoto childPhoto); 
	public ChildPhoto updateChildPhoto(ChildPhoto childPhto); 
	public ChildPhoto findChildPhotoByRegId(int id); 
}
