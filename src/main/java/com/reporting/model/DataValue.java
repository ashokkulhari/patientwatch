package com.reporting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataValue {
	
	private List<Header> headerList;
	private List<SubCol> subColList;
	
	
	public List<SubCol> createSubColList(){
		
//		Total    Admissions	Male	Female	0-6M	7-18 M	19-36M	37-60 M	>60 M	ST	SC	OBC	Others	PTG	AWW	ASHA	
//		OPD	Own	Other	WFH      < -4 SD	WFH      < -3 SD	WFH      < -2 SD	WFH      < -1 SD	Z score  
//		<-3SD	MUAC       < 11.5 CM	Bi-Pedal Edema	Total Available bed DAYS (bed x days of month)	Total Vacant Bed	
//		Total beds occupied	Bed Occupancy Rate %	<5 Days	5-10 Days	10-15 Days	>15 Days	Average Length of Stay	
//		Total Exits	Achieved 15% Wt. Gain	Avg. Wt. Gain	Cured	Defaulter	LAMA	Non respondent	Death	Upreferral	
//		Relapse Cases	Deaths during followup period (after discharge from MTC)	Appetite Test Failed	Followup 1	
//		Followup 2	Followup 3	Followup 4	Target for Followup	Followup done	% Follow up

//		total_admissions ,male ,female ,"
//    			+ "v_0_6m ,v_7_18m ,v_19_36m ,v_37_60m ,g_60m ,st ,sc ,obc ,others
		Map<String, HeaderDataValues> headerSubColNameMapping = new HashMap<>();
		
		headerSubColNameMapping.put("total_admissions",new HeaderDataValues("total_admissions","Total Admissions", "Admission"));
		headerSubColNameMapping.put("male",new HeaderDataValues("male","Male", "Admission"));
		headerSubColNameMapping.put("female",new HeaderDataValues("female","Female", "Admission"));
		
		headerSubColNameMapping.put("v_0_6m",new HeaderDataValues("v_0_6m","0-6M", "Age Group (in months)"));
		headerSubColNameMapping.put("v_7_18m",new HeaderDataValues("v_7_18m","7-18M", "Age Group (in months)"));
		headerSubColNameMapping.put("v_19_36m",new HeaderDataValues("v_19_36m","19-36M", "Age Group (in months)"));		
		headerSubColNameMapping.put("v_37_60m",new HeaderDataValues("v_37_60m","37-60 M", "Age Group (in months)"));
		headerSubColNameMapping.put("g_60m",new HeaderDataValues("g_60m",">60 M", "Age Group (in months)"));
		
		headerSubColNameMapping.put("st",new HeaderDataValues("st","ST", "Caste"));
		headerSubColNameMapping.put("sc",new HeaderDataValues("sc","SC", "Caste"));
		headerSubColNameMapping.put("obc",new HeaderDataValues("obc","OBC", "Caste"));
		headerSubColNameMapping.put("others",new HeaderDataValues("others","Others", "Caste"));
		
		ArrayList<Map<String, String>> subColVarNames = new ArrayList<>();
		
		return subColList;
	}
	
    }
