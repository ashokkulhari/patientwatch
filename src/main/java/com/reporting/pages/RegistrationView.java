package com.reporting.pages;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.reporting.entity.Admission;
import com.reporting.entity.AnthropometryDaily;
import com.reporting.entity.AnthropometryOne;
import com.reporting.entity.Block;
import com.reporting.entity.Child;
import com.reporting.entity.ChildPhoto;
import com.reporting.entity.District;
import com.reporting.entity.MTC;
import com.reporting.entity.Registration;
import com.reporting.services.LocationService;
import com.reporting.services.RegistrationService;
import com.reporting.util.FacesContextUtil;

@ManagedBean(name = "registrationView")
@SessionScoped
public class RegistrationView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{locationService}")
	private transient LocationService locationService;
	
	@ManagedProperty("#{registrationService}")
	private transient RegistrationService registrationService;
	
	private String district; 
	private List<String> districts;

	private String block; 
	private List<String> blocks;
    private String mtc;
    private List<String> mtcs;
    private String caste;
	private List<String> castes;
	private String refferedBy;
	private List<String> refferedBys;
	private String gender;
	private List<String> genders;
	private ChildPhoto childPhoto;
	private boolean disableAll;
	private StreamedContent photo;
	private AnthropometryOne anthropometryOne;
	private Registration registration;
	private Map<String,List<String>> locationData;
	private int imageId;

	
	public RegistrationView(){
		
		System.out.println("...................RegisterView.......constructor.............");
	}
	@PostConstruct
    public void init() {
    	System.out.println("...................RegisterView.......init.............");
    	FacesContext contextFaces = FacesContext.getCurrentInstance();
    	String samId =(String) contextFaces.getExternalContext().getSessionMap().get("samid");
    	System.out.println(" .......RegisterView................samid = "+samId);
    	
	    ServletContext servletContext = (ServletContext) contextFaces.getExternalContext().getContext();
	    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	    this.locationService=(LocationService) context.getBean("locationService");
	    this.registrationService=(RegistrationService)context.getBean("registrationService");
	    AdmissionView admissionView =FacesContextUtil.getBean(AdmissionView.class);
	    DailyEntryView dailyEntryView=FacesContextUtil.getBean(DailyEntryView.class);
	    List<Object> objList =null;//Admission,ChildPhoto,Anthro
	    if(samId!=null && !"".equals(samId.trim()) ){
	    	objList =this.registrationService.findAllRegistrationViewData(samId);
	    	if(objList.size()>0){
	    		Admission admission =(Admission) ((List)objList.get(0)).get(0);
	    		this.childPhoto =(ChildPhoto) ((List)objList.get(0)).get(1);
		    	this.anthropometryOne=(AnthropometryOne) ((List)objList.get(0)).get(2);
		    	
		    	registration =admission.getRegistration();
		    	this.district=registration.getMtc().getBlock().getDistrict().getDistrict_name();
			    this.block=registration.getMtc().getBlock().getBlock_name();
			    this.mtc=registration.getMtc().getMtc_name();
			    this.photo =getImage();
			    imageId=this.childPhoto.getChildPicId();
			    disableAll=true;
			    // Admission View
			    admissionView.setAdmission(admission);
		    	admissionView.setDisableAll(true);
		    	if(objList.size()>1){
		    		admissionView.setAnthropometryOne((AnthropometryOne) ((List)objList.get(1)).get(2));
		    	}else{
		    		admissionView.setAnthropometryOne(anthropometryOne );
		    	}
		    	// DailyEntry View
		    	dailyEntryView.setAnthropometryDaily(new AnthropometryDaily());
		    	dailyEntryView.getAnthropometryDaily().setAdmission(admission);
		    	dailyEntryView.updateResults(admission.getAdmissionId());
		    	
	    	}else{
	    		System.out.println("................NO record found ..........");
	    	}
	    	
    	}else{
    		setLocationsInit();
    		dailyEntryView.init();
        	this.districts=locationService.findAllDistrictNames();
        	this.locationData = locationService.findAllLocationNames();
        	this.childPhoto = new ChildPhoto();
        	this.anthropometryOne=new AnthropometryOne();
        	registration = new Registration();
        	registration.setChild(new Child());
        	System.out.println(locationData);
        	
        	setCasesInit();
        	
        	refferedBys = new ArrayList<String>();
        	refferedBys.add("ASHA");
        	refferedBys.add("Test");
        	genders = new ArrayList<String>();
        	genders.add("Male");
        	genders.add("Female");
        	
        	disableAll=false;
        	photo=null;
    	}
	    
    	
//    	loadLocationData();
    	
    }

	public void setLocationsInit() {
		this.district=null;
	    this.block=null;
	    this.mtc=null;
	    this.blocks=null;
	    this.districts=null;
	    this.mtcs=null;
	}

	public void setCasesInit() {
		castes = new ArrayList<String>();
    	castes.add("SC");
    	castes.add("ST");
    	castes.add("OBC");
	}


	public void loadLocationData() {
		List<Block> bk = locationService.findAllBlocks();
    	List<MTC> mtc = locationService.findAllMTCs();
    
    	List<String> values ;
			for (int j = 0; j < bk.size(); j++) {
				Block b = bk.get(j);
				District d =b.getDistrict();
				System.out.println("------d---"+d);
				if(d!=null){
					if(locationData.containsKey(d.getDistrict_name())){
						values=locationData.get(d.getDistrict_name());
					}else{
						values = new ArrayList<>();
					}
					values.add(b.getBlock_name());
					locationData.put(d.getDistrict_name(), values);
				}
				
				}
			for (int j = 0; j < mtc.size(); j++) {
				MTC m = mtc.get(j);
				Block b =m.getBlock();
				System.out.println("------b--"+b);
				if(b!=null){
					if(locationData.containsKey(b.getBlock_name())){
						values=locationData.get(b.getBlock_name());
					}else{
						values = new ArrayList<>();
					}
					values.add(m.getMtc_name());
					locationData.put(b.getBlock_name(), values);
				}
				
				}
	}
    
    	
	public void onDistrictChange() {
    	System.out.println("......onDistrictChange..............."+district);  
    	if(district !=null && !district.equals("")){
            this.setBlocks(locationData.get(district.trim()));
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
	
	 public void onPageRefresh() {
	        System.out.println("......onPageRefresh...............");   
	        init();
	 }   
	 
	 public void doRegister(){
		 System.out.println("......doRegister..............."+registration.getInvestigatorName() +
				 " , date ="+registration.getVisitDate() +" , child name ="+ registration.getChild().getChildName()
				 +"  , mtc name ="+mtc);   
		 Calendar cal = Calendar.getInstance();
		    cal.setTime(registration.getChild().getDateOfBirth());
		 int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH)+1;
		    int day = cal.get(Calendar.DAY_OF_MONTH);
		   System.out.println(year+" month ="+month +"  day= " +day); 
		   LocalDate birthdate = new LocalDate(year, month, day);
			LocalDate now = LocalDate.now();// test, in real world without args
			
			Years age = Years.yearsBetween(birthdate, now);
			Months mn =Months.monthsBetween(birthdate, now);
			System.out.println(""+ age.getYears()  + " " + mn.getMonths() );
			registration.getChild().setAgeYears(age.getYears());
			registration.getChild().setAgeMonths(mn.getMonths());
		 // Save Child
		 registrationService.saveChild(registration.getChild());
		 // save MTC id 
		 MTC m =locationService.findAllMTCByNames(mtc.trim());
		 registration.setMtc(m);
		 //Save Registration
		 registrationService.saveRegistration(registration);
		 System.out.println("After Registration SAVE ID  = "+registration.getRegistrationId());
		 
		 childPhoto.setRegistration(registration);
		 registrationService.saveChildPhoto(childPhoto);
		 anthropometryOne.setRegistration(registration);
		 anthropometryOne.setEntryTypeId(1);
		 registrationService.saveAnthropometryOne(anthropometryOne);
		 disableAll=true;
		 photo =getImage();
		 System.out.println("============================DONE=================================");
		 FacesMessage message = new FacesMessage("Registration Succesfull");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 
	 public void handleFileUpload(FileUploadEvent event) {
	    	System.out.println("..............handleFileUpload............."+event.getFile().getFileName()+"  - "+getChildPhoto());
	    	this.childPhoto.setChildPicName(event.getFile().getFileName());
	    	this.childPhoto.setImageByte(event.getFile().getContents());
	    	photo =getImage();
	    	imageId = (int) new Date().getTime();
	        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	 
	 public StreamedContent getImage() {
		 			System.out.println("  getImage = "+childPhoto.getImageByte().length);
		 		if(childPhoto.getImageByte()==null){System.out.println("...getting default image...");
		 			return new DefaultStreamedContent();
		 		}else{
		 			return new DefaultStreamedContent(new ByteArrayInputStream(childPhoto.getImageByte()),"image/jpg");
		 		}
	            
	    }

	    public List<String> getCastes() {
			return castes;
		}

		public void setCastes(List<String> castes) {
			this.castes = castes;
		}
		
	    
	    public Registration getRegistration() {
			return registration;
		}

		public void setRegistration(Registration registration) {
			this.registration = registration;
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

		public LocationService getLocationService() {
			return locationService;
		}


		public void setLocationService(LocationService locationService) {
			this.locationService = locationService;
		}
		public List<String> getDistricts() {
			return districts;
		}
		public void setDistricts(List<String> districts) {
			this.districts = districts;
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

		public RegistrationService getRegistrationService() {
			return registrationService;
		}

		public void setRegistrationService(RegistrationService registrationService) {
			this.registrationService = registrationService;
		}
		 public String getCaste() {
				return caste;
			}

			public void setCaste(String caste) {
				this.caste = caste;
			}
			
			public String getRefferedBy() {
				return refferedBy;
			}

			public void setRefferedBy(String refferedBy) {
				this.refferedBy = refferedBy;
			}

			public List<String> getRefferedBys() {
				return refferedBys;
			}

			public void setRefferedBys(List<String> refferedBys) {
				this.refferedBys = refferedBys;
			}
			
			public String getGender() {
				return gender;
			}

			public void setGender(String gender) {
				this.gender = gender;
			}

			public List<String> getGenders() {
				return genders;
			}

			public void setGenders(List<String> genders) {
				this.genders = genders;
			}
			
			public Map<String, List<String>> getLocationData() {
				return locationData;
			}

			public void setLocationData(Map<String, List<String>> locationData) {
				this.locationData = locationData;
			}
			
			public ChildPhoto getChildPhoto() {
				return childPhoto;
			}
			public void setChildPhoto(ChildPhoto childPhoto) {
				this.childPhoto = childPhoto;
			}
			
			public AnthropometryOne getAnthropometryOne() {
				return anthropometryOne;
			}
			public void setAnthropometryOne(AnthropometryOne anthropometryOne) {
				this.anthropometryOne = anthropometryOne;
			}
			public StreamedContent getPhoto() {
				return photo;
			}
			public void setPhoto(StreamedContent photo) {
				this.photo = photo;
			}
			public boolean isDisableAll() {
				return disableAll;
			}
			public void setDisableAll(boolean disableAll) {
				this.disableAll = disableAll;
			}
			public int getImageId() {
				return imageId;
			}
			public void setImageId(int imageId) {
				this.imageId = imageId;
			}
}
