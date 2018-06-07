package com.reporting.pages;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.reporting.model.Customer;
import com.reporting.model.SearchData;
import com.reporting.services.CustomerService;
import com.reporting.util.FacesContextUtil;
import com.reporting.util.Util;

@ManagedBean(name="login")
@SessionScoped
public class Login implements Serializable {

	 /**
	   * class logger.
	   */
	  private static final Logger LOG = LoggerFactory.getLogger(Login.class);

	  /**
	   * default serialVersionUID.
	   */
	 private static final long serialVersionUID = 1L;
	 private boolean loggedIn;

	@ManagedProperty("#{customerService}")
	private CustomerService customerService;

	private String c_name;
	private String c_password;
	List<String> finalList;
	String finalQueryStr;
	private String district;
	private String block;
	private String mtc;
	private String month;
	private Integer noOfBed;
	private String quater;
	private String districtFilters;
	
	private ArrayList<ArrayList<Object>> datas;
		
	public void setDbDatas(String district,String block,String mtc,String month,String noOfBeds,String quarter){
			if(this.finalQueryStr!=null && !"".equals(this.finalQueryStr.trim())){
	        	this.datas=customerService.getData(this.finalQueryStr.trim(),district,block,mtc,month,noOfBeds,quarter);
	        }else{
	        	this.datas=customerService.getData(district,block,mtc,month,noOfBeds,quarter);
	        }
		}
	public Login(){
		System.out.println(".................Login constructor.................");
	    // Get Spring's context from the servlet context.
	    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

	    //get the controller
	    this.customerService=(CustomerService) context.getBean("customerService");
	    setDbDatas(null,null,null,"'may'",null,null);
	}
	
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_password() {
		return c_password;
	}
	public void setC_password(String c_password) {
		this.c_password = c_password;
	}
	public String test() throws IOException{
		System.out.println("-----------test--------------");
		this.finalList=null;
		this.finalQueryStr=null;
		this.district=null;
		this.block=null;
		this.mtc=null;
		this.month=null;
		this.quater=null;
		this.noOfBed=null;
		this.districtFilters=null;
//		setDbDatas();
		FacesContext.getCurrentInstance().getExternalContext().redirect("finalreport.xhtml");
//		url="/pages/finalreport.xhtml"
		return "finalreport";
	}
	
	
	public String redirectDashboard() throws IOException{
		FacesContextUtil.getBean(RDashboardView.class).init();;
		FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
		return "dashboard";
	}
	public String redirectToRegistration() throws IOException{
		System.out.println("-----------redirectToRegistration--------------");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("samid", null);
		FacesContextUtil.getBean(RegistrationView.class).init();;
		FacesContextUtil.getBean(AdmissionView.class).init();;
		FacesContext.getCurrentInstance().getExternalContext().redirect("registration.xhtml");
		return "registration";
	}
	
	public String test2() throws IOException{
		System.out.println("-----------test--------------");
//		url="/pages/finalreport.xhtml"
		return "/pages/finalreport.xhtml";
	}
public String doLogin(){
		
		System.out.println("..............login called.............."+c_name);
		Customer customer = new Customer();
		customer.setc_name(c_name);
		customer.setc_password(c_password);
			customer = customerService.loginCustomer(customer);
			
			if (customer!=null) {System.out.println("..............................cid ==="+customer.getc_id());
			loggedIn = true;
//			HttpSession session = Util.getSession();
//			session.setAttribute("username", customer.getc_name());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", customer.getc_name());
			
			FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Welcome",
                    customer.getc_name()));
			
	       return "success";
	        } else {
	            FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_WARN,
	                    "Invalid Login!",
	                    "Please Try Again!"));
	            return "login";
	        }
		
	}
public String logout() {System.out.println(".logout..............");
//    HttpSession session = Util.getSession();
//    session.invalidate();
loggedIn = false;
FacesContext facesContext = FacesContext.getCurrentInstance();

HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(false);
System.out.println(httpSession.getId());
httpSession.invalidate();

System.out.println(httpSession.getId());
try {
	FacesContext.getCurrentInstance().getExternalContext().redirect("/reporting/login.xhtml");
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    return "/login.xhtml";
 }

public boolean isLoggedIn() {
	return loggedIn;
}

public void setLoggedIn(boolean loggedIn) {
	this.loggedIn = loggedIn;
}

  public List<SearchData> getSearchData(String samId){
	return this.customerService.searchListBySamId(samId);
  }


  public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getMtc() {
		return mtc;
	}

	public void setMtc(String mtc) {
		this.mtc = mtc;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getNoOfBed() {
		return noOfBed;
	}

	public void setNoOfBed(Integer noOfBed) {
		this.noOfBed = noOfBed;
	}

	public String getQuater() {
		return quater;
	}

	public void setQuater(String quater) {
		this.quater = quater;
	}
	
	public List<String> getFinalList() {
		return finalList;
	}

	public void setFinalList(List<String> finalList) {
		this.finalList = finalList;
	}

	public String getFinalQueryStr() {
		return finalQueryStr;
	}

	public void setFinalQueryStr(String finalQueryStr) {
		this.finalQueryStr = finalQueryStr;
	}
	public ArrayList<ArrayList<Object>> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<ArrayList<Object>> datas) {
		this.datas = datas;
	}
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String getDistrictFilters() {
		return districtFilters;
	}
	public void setDistrictFilters(String districtFilters) {
		this.districtFilters = districtFilters;
	}
}
