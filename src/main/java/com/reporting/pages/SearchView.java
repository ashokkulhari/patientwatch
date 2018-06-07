package com.reporting.pages;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.reporting.model.SearchData;
import com.reporting.util.FacesContextUtil;

@ManagedBean(name = "searchView")
@ViewScoped
public class SearchView implements Serializable{

	private static final long serialVersionUID = 1L;

	private String samId;
    private boolean istableenabled;
    private List<SearchData> results;
	
	

	@PostConstruct
    public void init() {
			System.out.println(".................Search init................"+samId);
			
			this.istableenabled=false;
	}		
	
	public void onSearch(){
		System.out.println("....................on search.............samId ="+samId);
		istableenabled=true;
		if(samId!=null && !"".equals(samId.trim())){
			results = FacesContextUtil.getBean(Login.class).getSearchData(samId.trim());
		}else{
			System.out.println("  ...........in search click SamID is null or empty.........");
		}
		
	}
	
	public void onSearchP(){
		System.out.println("....................on onSearchP.............samId ="+samId );
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("samid", samId);
			FacesContextUtil.getBean(RegistrationView.class).init();;
			FacesContext.getCurrentInstance().getExternalContext().redirect("registration.xhtml");
//			FacesContextUtil.getBean(AdmissionView.class).init();;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("...ending searchP...........");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("samid", null);
	}
	
	public void onSearchE(ActionEvent e){
		System.out.println("....................on onSearchE.............samId ="+samId +"  "+e);
		
//		registrationService.findAllRegistrationViewData(samId);
		
		// GET Admission from SAM ID
		// Get registration From Admission ID -- contains Child , MTC
		// child_pic from registration
		//  GET anthropometry_one_seq from registration
	}

	public boolean isIstableenabled() {
		return istableenabled;
	}

	public void setIstableenabled(boolean istableenabled) {
		this.istableenabled = istableenabled;
	}

	public String getSamId() {
		return samId;
	}

	public void setSamId(String samId) {
		this.samId = samId;
	}
	public List<SearchData> getResults() {
		return results;
	}

	public void setResults(List<SearchData> results) {
		this.results = results;
	}
}
