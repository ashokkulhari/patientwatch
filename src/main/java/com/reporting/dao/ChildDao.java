package com.reporting.dao;


import com.reporting.entity.Child;

public interface ChildDao {
	public void saveChild(Child child); 
	public Child updateChild(Child child); 
	public Child findChildById(int id); 
	public Child findChildByNameChild(String childName); 
}
