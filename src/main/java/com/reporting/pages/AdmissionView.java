package com.reporting.pages;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.reporting.entity.Admission;
import com.reporting.entity.AnthropometryOne;
import com.reporting.services.AdmissionService;
import com.reporting.services.RegistrationService;
import com.reporting.util.FacesContextUtil;


@ManagedBean(name = "admissionView")
@SessionScoped
public class AdmissionView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{registrationService}")
	private transient RegistrationService registrationService;
	@ManagedProperty("#{admissionService}")
	private transient AdmissionService admissionService;

	private AnthropometryOne anthropometryOne;
	private Admission admission;
	private boolean disableAll;
	
	
	public AdmissionView(){
		System.out.println("...............AdmissionView.......Constructor.........");
	}
	@PostConstruct
    public void init() {
    	System.out.println("...................AdmissionView.......init.............");
    	FacesContext contextFaces = FacesContext.getCurrentInstance();
    	String samId =(String) contextFaces.getExternalContext().getSessionMap().get("samid");
    	System.out.println(" .......RegisterView................samid = "+samId);
    	
	    ServletContext servletContext = (ServletContext) contextFaces.getExternalContext().getContext();
	    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	    this.registrationService=(RegistrationService)context.getBean("registrationService");
	    this.admissionService=(AdmissionService)context.getBean("admissionService");
	    if(samId!=null && !"".equals(samId.trim())){
//	    	this.admission =FacesContextUtil.getBean(RegistrationView.class).getAdmission();
//	    	this.anthropometryOne=FacesContextUtil.getBean(RegistrationView.class).getAnthropometryOne();
//	    	disableAll=true;
	    	
	    }else{
	    	disableAll=false;
		    this.admission = new Admission();
		    this.anthropometryOne=new AnthropometryOne();
	    }
	    
	}	
	public void doAdmit(){
		System.out.println("...................Admit.................."+admission.getRegistration());
		System.out.println(admission.getRegistration().getRegistrationId());
		String samid = admission.getRegistration().getMtc().getBlock().getDistrict().getZone().getZone_id() +"-"
		+String.format("%02d",admission.getRegistration().getMtc().getBlock().getDistrict().getDistrict_id())+"-"
		+String.format("%03d",admission.getRegistration().getMtc().getMtc_id()) +"-"
				+String.format("%05d",admission.getRegistration().getChild().getChildId());
		System.out.println(""+samid);
		admission.setSamId(samid);
		admissionService.saveAdmission(admission);
		disableAll=true;
		System.out.println("====================ADMISSION DONE==================");
		
	}
	public AnthropometryOne getAnthropometryOne() {
		return anthropometryOne;
	}
	public void setAnthropometryOne(AnthropometryOne anthropometryOne) {
		this.anthropometryOne = anthropometryOne;
	}
	public Admission getAdmission() {
		return admission;
	}
	public void setAdmission(Admission admission) {
		this.admission = admission;
	}
	public boolean isDisableAll() {
		return disableAll;
	}
	public void setDisableAll(boolean disableAll) {
		this.disableAll = disableAll;
	}
	
	public RegistrationService getRegistrationService() {
		return registrationService;
	}
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	public AdmissionService getAdmissionService() {
		return admissionService;
	}
	public void setAdmissionService(AdmissionService admissionService) {
		this.admissionService = admissionService;
	}
}
