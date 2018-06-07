package com.reporting.pages;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Iterator;
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

import org.primefaces.model.chart.ChartSeries;
 
@ManagedBean
public class ChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{customerService}")
	private CustomerService customerService;
	
	public CustomerService getCustomerService() {
		return customerService;
	}


	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private BarChartModel barModel;
	private BarChartModel barMTC_T_AD_Model;
    

	private HorizontalBarChartModel horizontalBarModel;
    private LineChartModel lineModel1;
    private LineChartModel lineModel2;
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
 
    @PostConstruct
    public void init() {
    	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

	    //get the controller
	    this.customerService=(CustomerService) context.getBean("customerService");
	    
    	createBarMTC_T_AD_Model();
        createBarModels();
        createLineModels();
        createPieModels();
    }
 
   
    private BarChartModel initBarMTC_T_AD_Model() {
        BarChartModel model = new BarChartModel();
	    Map<String, Integer> list =this.customerService.getMTC_TotalAdmFor20_10Beds();
        ChartSeries mtc_t_ad = new ChartSeries();
       int c=0;
        for (Iterator iterator = list.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			mtc_t_ad.set(key, list.get(key));
			c=c+list.get(key);
		}
        mtc_t_ad.setLabel("Total Admissions: " +c);
        model.addSeries(mtc_t_ad);
        return model;
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        Map<String, String> list =this.customerService.getMTC_Total_M_F_For20_10Beds(null,"20",null,null,null);
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
     
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
     
    private void createBarMTC_T_AD_Model() {
    	barMTC_T_AD_Model = initBarMTC_T_AD_Model();
    	barMTC_T_AD_Model.setBarPadding(5);
    	barMTC_T_AD_Model.setTitle("MTC Wise Total Admissions in May-2017 in 20 & 10 Bedded MTC’s ");
    	barMTC_T_AD_Model.setLegendPosition("ne");
    	
        Axis xAxis = barMTC_T_AD_Model.getAxis(AxisType.X);
        xAxis.setLabel("MTC");
        Axis yAxis = barMTC_T_AD_Model.getAxis(AxisType.Y);
        yAxis.setLabel("Total Admissions");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
    private void createBarModel() {
        barModel = initBarModel();
        barModel.setShowPointLabels(true);
        barModel.setTitle("Gender Wise Total Admissions in May-2017 in 20 & 10 Bedded MTC’s ");
        barModel.setLegendPosition("ne");
//        barModel.setZoom(true);
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total");
        yAxis.setTickFormat("%s-%d");
        yAxis.setMin(0);
        yAxis.setMax(400);
    } 
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);
 
        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);
         
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");        
    }
 
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
     
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Category Chart");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
     
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
     
    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        Map<String, Integer> list =this.customerService.getMTC_TotalAdmFor20_10Beds(); 
        for (Iterator iterator = list.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			pieModel1.set(key, list.get(key));
		}
        pieModel1.setShowDataLabels(true);
        pieModel1.setTitle("MTC Wise Total Admissions in May-2017 in 20 & 10 Bedded MTC’s ");
        pieModel1.setLegendPosition("w");
    }
     
    private void createPieModel2() {
        pieModel2 = new PieChartModel();
        Map<String, String> list =this.customerService.getMTC_Total_M_F_For20_10Beds(null,"20",null,null,null);
        int totalMale=0;
        int totalFemale=0;
        for (Iterator iterator = list.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String m_f=list.get(key);
			String [] values =m_f.split(" = ");
			int m=Integer.parseInt(values[0]);
			int f=Integer.parseInt(values[1]);
			totalMale=totalMale+m;
			totalFemale=totalFemale+f;
		}
        pieModel2.set("Male", totalMale);
        pieModel2.set("Female", totalFemale);
        pieModel2.setTitle("Gender Wise Total Admissions in May-2017 in 20 & 10 Bedded MTC’s ");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public BarChartModel getBarMTC_T_AD_Model() {
		return barMTC_T_AD_Model;
	}

	public void setBarMTC_T_AD_Model(BarChartModel barMTC_T_AD_Model) {
		this.barMTC_T_AD_Model = barMTC_T_AD_Model;
	}
	
	 public BarChartModel getBarModel() {
	        return barModel;
	    }
	     
	    public HorizontalBarChartModel getHorizontalBarModel() {
	        return horizontalBarModel;
	    }
	 
}