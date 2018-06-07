package com.reporting.pages;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.reporting.services.CustomerService;
import com.reporting.util.Util;

import org.primefaces.model.chart.ChartSeries;
 
@ManagedBean(name = "chartReportView")
public class ChartReportView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{customerService}")
	private CustomerService customerService;
	private BarChartModel barModel;

	private String district; 
    private String block; 
	
	private List<String> months;
	private String month;
	private List<Integer> noOfBeds;
	private Integer noOfBed;
	private List<String> quaters;
	private String quater;
    private Integer width;
	
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@PostConstruct
    public void init() {
    	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	    this.customerService=(CustomerService) context.getBean("customerService");
	    this.months =Util.getMonthList();
	    this.noOfBeds=Util.getNoOfBeds();
	    this.quaters=Util.getQuaters();
        createBarModels(null,"20",null,null,null);
        width=80;
    }
	
	public void submit(){
		System.out.println("----chart submit...."+month +"  noOfBed ="+noOfBed+"   quater ="
					+quater+"  district="+district+" block ="+block);
			
			
		String mn=null;
		if(month!=null && !"".equals(month.trim())){
			mn="'"+month+"'";
		}
		String nm=null;
		if(noOfBed>0){
			nm=""+noOfBed;
		}
		String qStr=null;
		if(quater!=null && !"".equals(quater.trim())){
			 qStr =Util.getQuaterMap().get(quater);
		}
		String dStr=null;
		if(district!=null && !"".equals(district.trim())){
			dStr="'"+district+"'";
		}
		String bStr=null;
		if(block!=null && !"".equals(block.trim())){
			bStr="'"+block+"'";
		}
		createBarModels(mn,nm,qStr,dStr,bStr);
	}
	
    private BarChartModel initBarModel(String month, String noOfBeds,String quaster,String district,String block) {
        BarChartModel model = new BarChartModel();
        Map<String, String> list =this.customerService.getMTC_Total_M_F_For20_10Beds(month,noOfBeds,quaster,district,block);
        System.out.println("----------------SIZE--------------"+list.size());
        if(list.size() >100 ){
			width=400;
		}else if (list.size() >80 && list.size() < 100){
			width=280;
		}else if (list.size() >70 && list.size() < 80){
			width=210;
		}else if (list.size() >50 && list.size() < 70){
			width=170;
		}else if (list.size() >20 && list.size() < 50){
			width=140;
		}else if (list.size() >8 && list.size() < 20){
			width=120;
		}else if (list.size() >5 && list.size() < 8){
			width=90;
		}else if (list.size() >3 && list.size() < 5){
			width=70;
		}
        
        ChartSeries boys = new ChartSeries();
        ChartSeries girls = new ChartSeries();
        ChartSeries boysAndgirls = new ChartSeries();
        int totalMale=0;
        int totalFemale=0;
        for (Iterator iterator = list.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String m_f=list.get(key);
			String [] values =m_f.split(" = ");
			int m=Integer.parseInt(values[0]);
			int f=Integer.parseInt(values[1]);
			boys.set(key, m);
			girls.set(key, f);
			boysAndgirls.set(key, (m+f));
			totalMale=totalMale+m;
			totalFemale=totalFemale+f;
		}
        boys.setLabel("Male: "+totalMale);
        girls.setLabel("Female: "+totalFemale);
        boysAndgirls.setLabel("Total: "+(totalMale+totalFemale));
        model.addSeries(boysAndgirls);
        model.addSeries(boys);
        model.addSeries(girls);
        return model;
    }
     
    private void createBarModels(String month, String noOfBeds,String quaster,String district,String block) {
        createBarModel( month,  noOfBeds, quaster,district,block);
    }
     
   
    private void createBarModel(String month, String noOfBeds,String quaster,String district,String block) {
        barModel = initBarModel( month,  noOfBeds, quaster,district,block);
        barModel.setShowPointLabels(true);
        barModel.setTitle("Gender Wise Total Admissions in May-2017 in 20 & 10 Bedded MTC’s ");
        barModel.setLegendPosition("ne");
//        barModel.setDatatipFormat("%s,%d%%");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("MTC");
        xAxis.setTickAngle(-60);
        
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total");
        yAxis.setTickFormat("%d");
        yAxis.setMin(0);
        yAxis.setMax(300);
    } 
    
        
    public void itemSelect(ItemSelectEvent event) {
    	System.out.println(event.getSource() +"  "+event.getComponent().getFamily());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
	 public BarChartModel getBarModel() {
	        return barModel;
	    }
	     
	 public CustomerService getCustomerService() {
			return customerService;
		}


		public void setCustomerService(CustomerService customerService) {
			this.customerService = customerService;
		}
		public List<String> getMonths() {
			return months;
		}
		public void setMonths(List<String> months) {
			this.months = months;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		
		public List<Integer> getNoOfBeds() {
			return noOfBeds;
		}
		public void setNoOfBeds(List<Integer> noOfBeds) {
			this.noOfBeds = noOfBeds;
		}
		public Integer getNoOfBed() {
			return noOfBed;
		}
		public void setNoOfBed(Integer noOfBed) {
			this.noOfBed = noOfBed;
		}
		public List<String> getQuaters() {
			return quaters;
		}
		public void setQuaters(List<String> quaters) {
			this.quaters = quaters;
		}
		public String getQuater() {
			return quater;
		}
		public void setQuater(String quater) {
			this.quater = quater;
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
}