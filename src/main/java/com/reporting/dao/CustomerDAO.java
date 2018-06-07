package com.reporting.dao;

import java.util.ArrayList;

import com.reporting.model.Customer;
import com.reporting.model.DashboardData;

public interface CustomerDAO {

	public void saveCustomer(Customer customer);
	public Customer loginCustomer(Customer customer);
	
	public ArrayList<ArrayList<Object>> getData(String district,String block,String mtc,String month,String noOfBeds,String quarter);
	public ArrayList<ArrayList<Object>> getData(String input,String district,String block,String mtc,String month,String noOfBeds,String quarter);
	
	public DashboardData getDashboadData(String districtName , String mtcName, String monthRange , String noOfBeds);
}
