package com.reporting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.reporting.model.Customer;
import com.reporting.model.DashboardData;
import com.reporting.model.SearchData;

public interface CustomerService {

	public void saveCustomer(Customer customer);
	public Customer loginCustomer(Customer customer);
	
	// FINAL REPORT
	public ArrayList<ArrayList<Object>> getData(String district,String block,String mtc,String month,String noOfBeds,String quarter);
	public ArrayList<ArrayList<Object>> getData(String input,String district,String block,String mtc,String month,String noOfBeds,String quarter);
	
	// DASHBOARD
	public DashboardData getDashboadData(String districtName , String mtcName, String monthRange , String noOfBeds);
	
	public Map<String , Integer> getMTC_TotalAdmFor20_10Beds();
	public Map<String , String> getMTC_Total_M_F_For20_10Beds(String month, String noOfBeds,String quaster,String district,String block);
	public List<SearchData> searchListBySamId(String samId);
}
