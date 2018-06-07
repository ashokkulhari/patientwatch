package com.reporting.pages;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.reporting.model.DashboardData;
import com.reporting.services.CustomerService;

@ManagedBean(name = "rDashboardView")
@SessionScoped
public class RDashboardView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DashboardData dashdoardData20_10_6_Current;
	private DashboardData dashdoardData20_10_6_Range;
	private DashboardData dashdoardData20_Current;
	private DashboardData dashdoardData20_Range;
	private DashboardData dashdoardData10_Current;
	private DashboardData dashdoardData10_Range;
	private DashboardData dashdoardData6_Current;
	private DashboardData dashdoardData6_Range;

	
	
	@ManagedProperty("#{customerService}")
		private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	@PostConstruct
    public void init() {
    	System.out.println("...................RDashboardView.......init.............");
    	FacesContext contextFaces = FacesContext.getCurrentInstance();
	    ServletContext servletContext = (ServletContext) contextFaces.getExternalContext().getContext();
	    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	    this.customerService=(CustomerService)context.getBean("customerService");
	    this.dashdoardData20_10_6_Current =this.customerService.getDashboadData(null, null,"'may'" ,"20,10,6");
	    this.dashdoardData20_10_6_Range =this.customerService.getDashboadData(null, null,"'may','april'" ,"20,10,6");
	    this.dashdoardData20_Current =this.customerService.getDashboadData(null, null,"'may'" ,"20");
	    this.dashdoardData20_Range =this.customerService.getDashboadData(null, null,"'may','april'" ,"20");
	    this.dashdoardData10_Current =this.customerService.getDashboadData(null, null,"'may'" ,"10");
	    this.dashdoardData10_Range =this.customerService.getDashboadData(null, null,"'may','april'" ,"10");
	    this.dashdoardData6_Current =this.customerService.getDashboadData(null, null,"'may'" ,"6");
	    this.dashdoardData6_Range =this.customerService.getDashboadData(null, null,"'may','april'" ,"6");
	}    
	
	public String onDistrictClick(){
		String value = FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("ImageName");
		try {
//			if(value!=null && value.equals("Ajmer")){
//				this.dashdoardData =this.customerService.getDashboadData("ajmer", null);
//				FacesContext.getCurrentInstance().getExternalContext().redirect("ajmer.xhtml");
//			}
////			Ajmer,CHC Kekri
//			if(value!=null && value.equals("Ajmer,CHC Kekri")){
//				String [] str = value.split(",");
//				this.dashdoardData =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase());
//				FacesContext.getCurrentInstance().getExternalContext().redirect("ajmer.xhtml");
//			}
			
			if(value!=null && !"".equals(value.trim())){
				if(value.contains(",")){
					String [] str = value.split(",");
					this.dashdoardData20_10_6_Current =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may'" ,"20,10,6");
					this.dashdoardData20_10_6_Current =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may'" ,"20,10,6");
				    this.dashdoardData20_10_6_Range =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may','april'" ,"20,10,6");
				    this.dashdoardData20_Current =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may'" ,"20");
				    this.dashdoardData20_Range =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may','april'" ,"20");
				    this.dashdoardData10_Current =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may'" ,"10");
				    this.dashdoardData10_Range =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may','april'" ,"10");
				    this.dashdoardData6_Current =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may'" ,"6");
				    this.dashdoardData6_Range =this.customerService.getDashboadData(str[0].toLowerCase(), str[1].toLowerCase(),"'may','april'" ,"6");
				
				}else{
					this.dashdoardData20_10_6_Current =this.customerService.getDashboadData(value.toLowerCase(), null,"'may'" ,"20,10,6");
					this.dashdoardData20_10_6_Current =this.customerService.getDashboadData(value.toLowerCase(), null,"'may'" ,"20,10,6");
				    this.dashdoardData20_10_6_Range =this.customerService.getDashboadData(value.toLowerCase(), null,"'may','april'" ,"20,10,6");
				    this.dashdoardData20_Current =this.customerService.getDashboadData(value.toLowerCase(), null,"'may'" ,"20");
				    this.dashdoardData20_Range =this.customerService.getDashboadData(value.toLowerCase(), null,"'may','april'" ,"20");
				    this.dashdoardData10_Current =this.customerService.getDashboadData(value.toLowerCase(), null,"'may'" ,"10");
				    this.dashdoardData10_Range =this.customerService.getDashboadData(value.toLowerCase(), null,"'may','april'" ,"10");
				    this.dashdoardData6_Current =this.customerService.getDashboadData(value.toLowerCase(), null,"'may'" ,"6");
				    this.dashdoardData6_Range =this.customerService.getDashboadData(value.toLowerCase(), null,"'may','april'" ,"6");
				}
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("ajmer.xhtml");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(".............ImageName .value------------"+value);
			  return "charts";
	}
	public String onDistrictClick2(){
		String value = FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("ImageName");
		System.out.println(".............ImageName .value------------"+value);
			  return "charts.xhtml";
	}

	
	public DashboardData getDashdoardData20_10_6_Current() {
		return dashdoardData20_10_6_Current;
	}


	public void setDashdoardData20_10_6_Current(DashboardData dashdoardData20_10_6_Current) {
		this.dashdoardData20_10_6_Current = dashdoardData20_10_6_Current;
	}


	public DashboardData getDashdoardData20_10_6_Range() {
		return dashdoardData20_10_6_Range;
	}


	public void setDashdoardData20_10_6_Range(DashboardData dashdoardData20_10_6_Range) {
		this.dashdoardData20_10_6_Range = dashdoardData20_10_6_Range;
	}


	public DashboardData getDashdoardData20_Current() {
		return dashdoardData20_Current;
	}


	public void setDashdoardData20_Current(DashboardData dashdoardData20_Current) {
		this.dashdoardData20_Current = dashdoardData20_Current;
	}


	public DashboardData getDashdoardData20_Range() {
		return dashdoardData20_Range;
	}


	public void setDashdoardData20_Range(DashboardData dashdoardData20_Range) {
		this.dashdoardData20_Range = dashdoardData20_Range;
	}


	public DashboardData getDashdoardData10_Current() {
		return dashdoardData10_Current;
	}


	public void setDashdoardData10_Current(DashboardData dashdoardData10_Current) {
		this.dashdoardData10_Current = dashdoardData10_Current;
	}


	public DashboardData getDashdoardData10_Range() {
		return dashdoardData10_Range;
	}


	public void setDashdoardData10_Range(DashboardData dashdoardData10_Range) {
		this.dashdoardData10_Range = dashdoardData10_Range;
	}


	public DashboardData getDashdoardData6_Current() {
		return dashdoardData6_Current;
	}


	public void setDashdoardData6_Current(DashboardData dashdoardData6_Current) {
		this.dashdoardData6_Current = dashdoardData6_Current;
	}


	public DashboardData getDashdoardData6_Range() {
		return dashdoardData6_Range;
	}


	public void setDashdoardData6_Range(DashboardData dashdoardData6_Range) {
		this.dashdoardData6_Range = dashdoardData6_Range;
	}
}
