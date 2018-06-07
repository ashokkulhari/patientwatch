package com.reporting.pages;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.component.column.Column;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.row.Row;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.reporting.entity.Admission;
import com.reporting.entity.AnthropometryDaily;
import com.reporting.services.AdmissionService;
import com.reporting.services.RegistrationService;
import com.reporting.util.Util;

@ManagedBean(name = "dailyEntryView")
@SessionScoped
public class DailyEntryView implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@ManagedProperty("#{admissionService}")
		private transient AdmissionService admissionService;
		
		public void setAdmissionService(AdmissionService admissionService) {
			this.admissionService = admissionService;
		}
		private AnthropometryDaily anthropometryDaily;
		
		private String day;
		private List<String> days;
		private List<AnthropometryDaily>  anthropometryResults;
		private String curred;
		public String getCurred() {
			return curred;
		}
		public void setCurred(String curred) {
			this.curred = curred;
		}
		private List<String> curreds;
		public List<String> getCurreds() {
			return curreds;
		}
		public void setCurreds(List<String> curreds) {
			this.curreds = curreds;
		}
		
		@PostConstruct
 	    public void init() {
	    	System.out.println("...................dailyEntryView.......init.............");
	    	FacesContext contextFaces = FacesContext.getCurrentInstance();
		    ServletContext servletContext = (ServletContext) contextFaces.getExternalContext().getContext();
		    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		    this.admissionService=(AdmissionService)context.getBean("admissionService");
		    this.anthropometryResults=null;
	    	this.anthropometryDaily = new AnthropometryDaily();
	    	this.curreds=Util.getDischagedStatus();
	    	loadTableData();
	    	
		}	
		public void saveRow(){
			System.out.println("..................saveRow..............day ="+anthropometryDaily.getDay());
			admissionService.saveAnthropometryDaily(anthropometryDaily);
			Admission admission =anthropometryDaily.getAdmission();
			updateResults(admission.getAdmissionId());
			this.anthropometryDaily = new AnthropometryDaily();
			anthropometryDaily.setAdmission(admission);
			
		}
		public void loadTableData() {
			if(anthropometryResults!=null){
	    		this.days=Util.getDaysNo(anthropometryResults.size());
	    	}else{
	    		this.days=Util.getDaysNo(0);
	    	}
		}
		
		public void updateResults(int admissionId){
		    anthropometryResults = admissionService.findAllForAdmission(admissionId);
		    loadTableData();
		}
		
		
		public AnthropometryDaily getAnthropometryDaily() {
			return anthropometryDaily;
		}

		public void setAnthropometryDaily(AnthropometryDaily anthropometryDaily) {
			this.anthropometryDaily = anthropometryDaily;
		}

		public String getDay() {
			return day;
		}

		public void setDay(String day) {
			this.day = day;
		}
		public List<String> getDays() {
			return days;
		}
		public void setDays(List<String> days) {
			this.days = days;
		}
		
		
		public List<AnthropometryDaily> getAnthropometryResults() {
			return anthropometryResults;
		}
		public void setAnthropometryResults(List<AnthropometryDaily> anthropometryResults) {
			this.anthropometryResults = anthropometryResults;
		}

}
