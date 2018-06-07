package com.reporting.pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.reporting.services.LocationService;
 
@ManagedBean
@ViewScoped
public class DropdownView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String district; 
    private String block; 
    private String mtc; 
    private List<String> districts;
    private List<String> blocks;
    public List<String> getDistricts() {
		return districts;
	}

	public void setDistricts(List<String> districts) {
		this.districts = districts;
	}

	public List<String> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<String> blocks) {
		this.blocks = blocks;
	}

	public List<String> getMtcs() {
		return mtcs;
	}

	public void setMtcs(List<String> mtcs) {
		this.mtcs = mtcs;
	}

	private List<String> mtcs;
    
    private boolean islocationrender;
    
    @ManagedProperty("#{locationService}")
	private transient LocationService locationService;
    private Map<String,List<String>> locationData;
	public Map<String, List<String>> getLocationData() {
		return locationData;
	}

	public void setLocationData(Map<String, List<String>> locationData) {
		this.locationData = locationData;
	}
    
	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public boolean isIslocationrender() {
		return islocationrender;
	}

	public void setIslocationrender(boolean islocationrender) {
		this.islocationrender = islocationrender;
	}
    
	public DropdownView(){
		System.out.println("--construct--location..."+isIslocationrender());
		
	}
	
	@PostConstruct
    public void init() {System.out.println("---int -location..."+isIslocationrender());
		    if(isIslocationrender()){
		    	setIslocationrender(false);
		    }
		    
		    
		    FacesContext contextFaces = FacesContext.getCurrentInstance();
		    ServletContext servletContext = (ServletContext) contextFaces.getExternalContext().getContext();
		    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		    this.locationService=(LocationService) context.getBean("locationService");
		    
	    	this.districts=locationService.findAllDistrictNames();
	    	this.locationData = locationService.findAllLocationNames();
         
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

	
	public void onDistrictChange() {
    	System.out.println("......onDistrictChange..............."+district);  
    	if(district !=null && !district.equals("")){
            blocks =locationData.get(district.trim());
            System.out.println("--blocks--"+blocks);
    	}else{
            blocks = new ArrayList<>();
    	}
     }
	 public void onBlockChange() {
	        System.out.println("......onBlockChange..............."+block);       
	        if(block !=null && !block.equals("")){
	            mtcs = locationData.get(block.trim()); System.out.println("mtcs= "+mtcs);
	        }else{
	        	mtcs = new ArrayList<>();
	        }	
	    }
    public void displayLocation() {
        FacesMessage msg;
        if(block != null && district != null)
            msg = new FacesMessage("Selected", block + " of " + district);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Block is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void onLocationRender() {System.out.println("...onLocationRender..."+this.islocationrender +"  "+isIslocationrender());
//	    if(isIslocationrender()){
//	    	setIslocationrender(false);
//	    }else{
//	    	setIslocationrender(true);
//	    }
    
    
    }
}