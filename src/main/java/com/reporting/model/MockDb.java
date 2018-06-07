package com.reporting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class MockDb {
	
	
    public static List<Header> getHeaderList(List<String> finalColHeaders) {
    	
    	/** All Header column list   **/
    	Map<String, HeaderDataValues> headerSubColNameMapping = getColumnsMap();
		
    	if(finalColHeaders!=null && finalColHeaders.size()>0){
    		headerSubColNameMapping =getUpdatedColumnsMap(headerSubColNameMapping, finalColHeaders);
    	}else{
    		System.out.println("Filtered HeaderCol list is null or empty..................");
    	}
    	
    	/** Start processing Table Headers and Sub Headers  **/
        List<Header> headerList = new ArrayList<Header>();

        List<SubCol> subColList_1 = new ArrayList<SubCol>();
        subColList_1.add(new SubCol("Name Of District", "districts"));
        
        headerList.add(new Header("District", subColList_1));

        List<SubCol> subColList_2 = new ArrayList<SubCol>();
        subColList_2.add(new SubCol("Name of MTC", "mtc_name"));
        headerList.add(new Header("MTC", subColList_2));
        
        /* updated filtered Header column list */
        Map<String, ArrayList<SubCol>> values = getHeaderSubColMap(headerSubColNameMapping);
        
        for (Iterator iterator = values.keySet().iterator(); iterator.hasNext();) {
			String headerNameKey =(String) iterator.next();
			List<SubCol> subColList_3=values.get(headerNameKey);
			headerList.add(new Header(headerNameKey, subColList_3));
		}
        

        return headerList;
    }

	public static Map<String, ArrayList<SubCol>> getHeaderSubColMap(
			Map<String, HeaderDataValues> headerSubColNameMapping) {
		ArrayList<SubCol>  cols;
        Map<String , ArrayList<SubCol>> values = new LinkedHashMap<String , ArrayList<SubCol>>();
        for (Iterator iterator = headerSubColNameMapping.keySet().iterator(); iterator.hasNext();) {
			String keySubColVar = (String) iterator.next();
			HeaderDataValues headerSubColValues=headerSubColNameMapping.get(keySubColVar);
			SubCol subcol = new SubCol();
			subcol.setName(headerSubColValues.getSubColName());
			subcol.setData(headerSubColValues.getSubColDataName());
			
			if(values.containsKey(headerSubColValues.getHeaderName())){
				cols=values.get(headerSubColValues.getHeaderName());
				cols.add(subcol);
				values.put(headerSubColValues.getHeaderName(), cols);
			}else{
				cols = new ArrayList<>();
				cols.add(subcol);
				values.put(headerSubColValues.getHeaderName(), cols);
			}
		}
		return values;
	}

	public static Map<String, HeaderDataValues> getColumnsMap() {
		Map<String, HeaderDataValues> headerSubColNameMapping = new LinkedHashMap<>();

		headerSubColNameMapping.put("total_admissions",new HeaderDataValues("total_admissions","Total Admissions", "Admissions"));
		headerSubColNameMapping.put("male",new HeaderDataValues("male","Male", "Admissions"));
		headerSubColNameMapping.put("female",new HeaderDataValues("female","Female", "Admissions"));

		headerSubColNameMapping.put("v_0_6m",new HeaderDataValues("v_0_6m","0-6M", "Age Group (in months)"));
		headerSubColNameMapping.put("v_7_18m",new HeaderDataValues("v_7_18m","7-18M", "Age Group (in months)"));
		headerSubColNameMapping.put("v_19_36m",new HeaderDataValues("v_19_36m","19-36M", "Age Group (in months)"));	
		headerSubColNameMapping.put("v_37_60m",new HeaderDataValues("v_37_60m","37-60 M", "Age Group (in months)"));
		headerSubColNameMapping.put("g_60m",new HeaderDataValues("g_60m",">60 M", "Age Group (in months)"));

		headerSubColNameMapping.put("st",new HeaderDataValues("st","ST", "Caste"));
		headerSubColNameMapping.put("sc",new HeaderDataValues("sc","SC", "Caste"));
		headerSubColNameMapping.put("obc",new HeaderDataValues("obc","OBC", "Caste"));
		headerSubColNameMapping.put("others",new HeaderDataValues("others","Others", "Caste"));
		headerSubColNameMapping.put("ptg",new HeaderDataValues("ptg","PTG", "Caste"));

		headerSubColNameMapping.put("aww",new HeaderDataValues("aww","AWW", "Referrals By"));
		headerSubColNameMapping.put("asha",new HeaderDataValues("asha","ASHA", "Referrals By"));
		headerSubColNameMapping.put("opd",new HeaderDataValues("opd","OPD", "Referrals By"));
		headerSubColNameMapping.put("own",new HeaderDataValues("own","OWN", "Referrals By"));
		headerSubColNameMapping.put("others_ref",new HeaderDataValues("others_ref","OTHERS", "Referrals By"));


		headerSubColNameMapping.put("wfh4",new HeaderDataValues("wfh4","WFH < -4SD", "Admission Criteria"));
		headerSubColNameMapping.put("wfh3",new HeaderDataValues("wfh3","WFH < -3SD", "Admission Criteria"));
		headerSubColNameMapping.put("wfh2",new HeaderDataValues("wfh2","WFH < -2SD", "Admission Criteria"));
		headerSubColNameMapping.put("wfh1",new HeaderDataValues("wfh1","WFH < -1SD", "Admission Criteria"));
		headerSubColNameMapping.put("z_score",new HeaderDataValues("z_score","Z Score <= -3SD", "Admission Criteria"));
		headerSubColNameMapping.put("muac",new HeaderDataValues("muac","MUAC < 11.5 CM", "Admission Criteria"));
		headerSubColNameMapping.put("oedema",new HeaderDataValues("oedema","Bi-Pedal Oedema", "Admission Criteria"));

		headerSubColNameMapping.put("tabd",new HeaderDataValues("tabd","Total Available Bed Days", "Bed Occupancy"));
		headerSubColNameMapping.put("tbo",new HeaderDataValues("tbo","Total Beds Occupied", "Bed Occupancy"));
		headerSubColNameMapping.put("tvb",new HeaderDataValues("tvb","Total Vacant Bed", "Bed Occupancy"));
		headerSubColNameMapping.put("bor",new HeaderDataValues("bor","Bed Occupancy Rate (in percentage)", "Bed Occupancy"));

		headerSubColNameMapping.put("v_less_5d",new HeaderDataValues("v_less_5d"," < 5 Days", " Length of Stay"));
		headerSubColNameMapping.put("v_5_10d",new HeaderDataValues("v_5_10d","5 -10 Days", " Length of Stay"));
		headerSubColNameMapping.put("v_11_15d",new HeaderDataValues("v_11_15d","11-15 Days", " Length of Stay"));
		headerSubColNameMapping.put("v_g_15d",new HeaderDataValues("v_g_15d"," > 15 Days", " Length of Stay"));
		headerSubColNameMapping.put("als",new HeaderDataValues("als","Average Length of Stay", " Length of Stay"));

		headerSubColNameMapping.put("t_exit",new HeaderDataValues("t_exit","Total Exits", "Exit Indicators"));
		headerSubColNameMapping.put("discharge_r",new HeaderDataValues("discharge_r","Discharge Rate", "Exit Indicators"));
		headerSubColNameMapping.put("wtg15",new HeaderDataValues("wtg15","No. of Children Achieved 15% Weight Gain", "Exit Indicators"));
		headerSubColNameMapping.put("awg",new HeaderDataValues("awg","Average Weight Gain", "Exit Indicators"));
		headerSubColNameMapping.put("cured",new HeaderDataValues("cured","Cured", "Exit Indicators"));
		headerSubColNameMapping.put("recovery_r",new HeaderDataValues("recovery_r","Recovery Rate", "Exit Indicators"));
		headerSubColNameMapping.put("p_recovered",new HeaderDataValues("p_recovered","Partially Recovered", "Exit Indicators"));
		headerSubColNameMapping.put("p_recovered_r",new HeaderDataValues("p_recovered_r","Partially Recovered Rate", "Exit Indicators"));
		headerSubColNameMapping.put("non_respondents",new HeaderDataValues("non_respondents","Non Respondents", "Exit Indicators"));
		headerSubColNameMapping.put("non_respondent_r",new HeaderDataValues("non_respondent_r","Non Respondents Rate", "Exit Indicators"));
		headerSubColNameMapping.put("defaulter",new HeaderDataValues("defaulter","Defaulters", "Exit Indicators"));
		headerSubColNameMapping.put("defaulter_r",new HeaderDataValues("defaulter_r","Defaulters Rate", "Exit Indicators"));
		headerSubColNameMapping.put("lama",new HeaderDataValues("lama","LAMA", "Exit Indicators"));
		headerSubColNameMapping.put("lama_r",new HeaderDataValues("lama_r","LAMA Rate", "Exit Indicators"));
		headerSubColNameMapping.put("up_referral",new HeaderDataValues("up_referral","Upreferrals", "Exit Indicators"));
		headerSubColNameMapping.put("up_ref_r",new HeaderDataValues("up_ref_r","Upreferrals Rate", "Exit Indicators"));
		headerSubColNameMapping.put("death",new HeaderDataValues("death","Death", "Exit Indicators"));
		headerSubColNameMapping.put("death_r",new HeaderDataValues("death_r","Death Rate", "Exit Indicators"));

		headerSubColNameMapping.put("relapse",new HeaderDataValues("relapse","Relapse Cases", "Other Outcomes"));
		headerSubColNameMapping.put("death_dfp",new HeaderDataValues("death_dfp","Death During Follow-up Period (After discharge from MTC)", "Other Outcomes"));
		headerSubColNameMapping.put("appetite",new HeaderDataValues("appetite","Appetite Test Failed", "Other Outcomes"));

		headerSubColNameMapping.put("f1",new HeaderDataValues("f1","Follow-up 1", "Follow-up"));
		headerSubColNameMapping.put("f2",new HeaderDataValues("f2","Follow-up 2", "Follow-up"));
		headerSubColNameMapping.put("f3",new HeaderDataValues("f3","Follow-up 3", "Follow-up"));
		headerSubColNameMapping.put("f4",new HeaderDataValues("f4","Follow-up 4", "Follow-up"));
		headerSubColNameMapping.put("tff",new HeaderDataValues("tff","Target for Follow-up", "Follow-up"));
		headerSubColNameMapping.put("fd",new HeaderDataValues("fd","Follow-up Done", "Follow-up"));
		headerSubColNameMapping.put("fp",new HeaderDataValues("fp","Follow-up Percentage", "Follow-up"));


		
		return headerSubColNameMapping;
	}

	public static Map<String, HeaderDataValues> getUpdatedColumnsMap(Map<String, HeaderDataValues> headerSubColNameMapping ,List<String> finalColList) {
		Map<String, HeaderDataValues> headerSubColNameMappingnew = new LinkedHashMap<>();
		
		for (int i = 0; i < finalColList.size(); i++) {
			String keyColVal=finalColList.get(i);
			if(headerSubColNameMapping.containsKey(keyColVal)){
				headerSubColNameMappingnew.put(keyColVal,headerSubColNameMapping.get(keyColVal));
			}
		} 
		return headerSubColNameMappingnew;
	}
//    public static List<ArrayList<Object>> getValueList(ArrayList<Object> datas) {
//    	
//    	System.out.println();
//        /*Create 5 rows for data array for 15 columns*/
//        List<ArrayList<Object>> valueList = new ArrayList<ArrayList<Object>>();
//        for(int i=1; i <datas.size(); i++) {
//        	 DataValue data = new DataValue(datas.get(i));
//            valueList.add(datas.get(i));
//        }
//        
//      valueList.add(datas);
//
//        return valueList;
//    }
//    
    
}
