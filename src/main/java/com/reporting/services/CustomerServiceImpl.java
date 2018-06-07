package com.reporting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.dao.CustomerDAO;
import com.reporting.model.Customer;
import com.reporting.model.DashboardData;
import com.reporting.model.ReportsUtilModel;
import com.reporting.model.SearchData;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private ReportsUtilModel reportsUtilModel;
	

	public ReportsUtilModel getReportsUtilModel() {
		return reportsUtilModel;
	}
	public void setReportsUtilModel(ReportsUtilModel reportsUtilModel) {
		this.reportsUtilModel = reportsUtilModel;
	}
	public void setCustomerDAO(CustomerDAO customerDAO){
		this.customerDAO =customerDAO;
	}
	@Override
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}
	@Override
	public Customer loginCustomer(Customer customer) {
		return customerDAO.loginCustomer(customer);
	}

	@Override
	public ArrayList<ArrayList<Object>> getData(String district,String block,String mtc,String month,String noOfBeds,String quarter){
		return customerDAO.getData(district,block,mtc,month,noOfBeds,quarter);
	}

	@Override
	public ArrayList<ArrayList<Object>> getData(String input,String district,String block,String mtc,String month,String noOfBeds,String quarter){
		return customerDAO.getData(input,district,block,mtc,month,noOfBeds,quarter);
	}
	@Override
	public DashboardData getDashboadData(String districtName, String mtcName, String monthRange , String noOfBeds) {
		return customerDAO.getDashboadData(districtName, mtcName,monthRange,noOfBeds);
	}
	@Override
	public Map<String, Integer> getMTC_TotalAdmFor20_10Beds() {
		return reportsUtilModel.getMTC_TotalAdmFor20_10Beds();
	}
	@Override
	public Map<String, String> getMTC_Total_M_F_For20_10Beds(String month, String noOfBeds,String quaster,String district,String block) {
		return reportsUtilModel.getMTC_Total_M_F_For20_10Beds( month,  noOfBeds, quaster,district,block);
	}
	@Override
	public List<SearchData> searchListBySamId(String samId) {
		return reportsUtilModel.searchListBySamId(samId);
	}
}
