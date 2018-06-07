package com.reporting.pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.reporting.services.LocationService;
import com.reporting.util.FacesContextUtil;
import com.reporting.util.Util;

	
@ManagedBean(name = "finalDDView")
@ViewScoped
public class FinalDDView  implements Serializable{
	         
		private static final long serialVersionUID = 1L;
		private String district; 
	    private String block; 
	    private String mtc; 
	    private List<String> districts;
	    private List<String> blocks;
		private List<String> mtcs;
	    private String month;
	    private List<String> months;
		private List<Integer> noOfBeds;
		private Integer noOfBed;
		private List<String> quaters;
		private String quater;
		private DualListModel<String> districtpicks;
		private String districtfilter;
		
		
		private boolean islocationrender;
	    
	    @ManagedProperty("#{locationService}")
		private transient LocationService locationService;
	    
	    @ManagedProperty("#{login}")
		private transient Login loginBean;
	    
	    
		private Map<String,List<String>> locationData;
		public Map<String, List<String>> getLocationData() {
			return locationData;
		}
		
		public FinalDDView(){
			System.out.println("---Constructor -final location..."+isIslocationrender());
		}
		@PostConstruct
	    public void init() {System.out.println("\n---int -finalDDView location..."+isIslocationrender());
			    if(isIslocationrender()){
			    	setIslocationrender(false);
			    	district=null;block=null;mtc=null;
			    }
			    FacesContext contextFaces = FacesContext.getCurrentInstance();
			    ServletContext servletContext = (ServletContext) contextFaces.getExternalContext().getContext();
			    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			    this.locationService=(LocationService) context.getBean("locationService");
			    
		    	this.districts=locationService.findAllDistrictNames();
		    	this.locationData = locationService.findAllLocationNames();
		    	this.months =Util.getMonthList();
			    this.noOfBeds=Util.getNoOfBeds();
			    this.quaters=Util.getQuaters();
			    
			  //Cities
		        List<String> districtsSource = this.districts;
		        List<String> districtsTarget = new ArrayList<String>();
		         
		        districtpicks = new DualListModel<String>(districtsSource, districtsTarget);
		        
	    }

		public void onDistrictFilterSelect(){
			System.out.println("...........onDistrictFilterSelect............"+districtfilter);
			this.loginBean = FacesContextUtil.getBean(Login.class);
			if(districtfilter!=null && districtfilter.equals("multi")){
				loginBean.setDistrict(null);
				loginBean.setBlock(null);
				loginBean.setMtc(null);
				
			}else if(districtfilter!=null && districtfilter.equals("single")){
				loginBean.setDistrictFilters(null);
				districtpicks.setTarget(new ArrayList<String>());
				districtpicks.setSource(this.districts);
			}
			
		}
		public void onTransfer(TransferEvent event) {
			System.out.println("...............onTransfer............."+districtpicks.getTarget()  );
			if(districtpicks!=null && districtpicks.getTarget()!=null && districtpicks.getTarget().size()>0){
				StringBuilder queryStr= new StringBuilder();
				for (int i = 0; i < districtpicks.getTarget().size(); i++) {
					if(i==0){
						queryStr.append("'"+districtpicks.getTarget().get(i)+"'");
					}else{
						queryStr.append(","+"'"+districtpicks.getTarget().get(i)+"'");
					}
				}
				this.loginBean = FacesContextUtil.getBean(Login.class);
				loginBean.setDistrictFilters(queryStr.toString());
			}
		       
		}        
		public void onDistrictChange() {
	    	System.out.println("......onDistrictChange..............."+district);  
	    	 this.loginBean = FacesContextUtil.getBean(Login.class);
	    	if(district !=null && !district.equals("")){
	    		this.loginBean.setDistrict(district);
	            blocks =locationData.get(district.trim());
	            System.out.println("--blocks--"+blocks);
	    	}else{this.loginBean.setDistrict(null);
	            blocks = new ArrayList<>();
	    	}
	     }
		 public void onBlockChange() {
		        System.out.println("......onBlockChange..............."+block);  
		        this.loginBean = FacesContextUtil.getBean(Login.class);
		        if(block !=null && !block.equals("")){
		        	this.loginBean.setBlock(block);
		            mtcs = locationData.get(block.trim()); System.out.println("mtcs= "+mtcs);
		        }else{this.loginBean.setBlock(null);
		        	mtcs = new ArrayList<>();
		        }	
		    }
		 public void onMTCChange() {
		        System.out.println("......onMTCChange..............."+mtc);  
		        this.loginBean = FacesContextUtil.getBean(Login.class);
		        if(mtc !=null && !mtc.equals("")){
		        	this.loginBean.setMtc(mtc);
		        }else{this.loginBean.setMtc(null);
		        }	
		    }
		 public void onMonthChange() {
		        System.out.println("......onMonthChange..............."+month);  
		        this.loginBean = FacesContextUtil.getBean(Login.class);
		        if(month !=null && !month.equals("")){
		        	this.loginBean.setMonth(month);
		        }else{this.loginBean.setMonth(null);
		        }	
		    }
		 public void onNoOfBedChange() {
		        System.out.println("......onNoOfBedChange..............."+noOfBed);  
		        this.loginBean = FacesContextUtil.getBean(Login.class);
		        if(noOfBed !=null && noOfBed >0){
		        	this.loginBean.setNoOfBed(noOfBed);
		        }else{this.loginBean.setNoOfBed(null);
		        }	
		    }
		 public void onQuarterChange() {
		        System.out.println("......onQuarterChange..............."+quater);  
		        this.loginBean = FacesContextUtil.getBean(Login.class);
		        if(quater !=null && !quater.equals("")){
		        	this.loginBean.setQuater(quater);
		        }else{this.loginBean.setQuater(null);
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
	    
	    public void onLocationRender() {System.out.println("..final.onLocationRender..."+this.islocationrender +"  "+isIslocationrender());
	    	if(!this.islocationrender){
	    		district=null;
	    		block=null;
	    		mtc=null;
	    	}
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
		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public List<String> getMonths() {
			return months;
		}

		public void setMonths(List<String> months) {
			this.months = months;
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

		public Login getLoginBean() {
			return loginBean;
		}

		public void setLoginBean(Login loginBean) {
			this.loginBean = loginBean;
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

		public DualListModel<String> getDistrictpicks() {
			return districtpicks;
		}

		public void setDistrictpicks(DualListModel<String> districtpicks) {
			this.districtpicks = districtpicks;
		}

		public String getDistrictfilter() {
			return districtfilter;
		}

		public void setDistrictfilter(String districtfilter) {
			this.districtfilter = districtfilter;
		}

		

	}