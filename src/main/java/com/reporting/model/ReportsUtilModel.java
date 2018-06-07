package com.reporting.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.util.Util;

public class ReportsUtilModel {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public Map<String , String> getMTC_Total_M_F_For20_10Beds(String month, String noOfBeds,String quarter,String district,String block){
		System.out.println("----getMTC_Total_M_F_For20_10Beds...."+month +"  "+noOfBeds+"   "+quarter);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Map<String,String> reportChart1=new LinkedHashMap();
		String s="";
		s = Util.formFilters(district, block, null, month, noOfBeds, quarter, s);
		
		String sql = "select f.mtc_name,f.male,f.female,(f.male+f.female) as a from final_report f LEFT OUTER JOIN mtc m on "
				+ " m.mtc_id=f.mtc_type_id  LEFT OUTER JOIN block b on m.block_type_id = b.block_id " 
				+" LEFT OUTER JOIN district d on d.district_id =b.district_type_id "
				+ s +" order by a DESC NULLS LAST ";        
		try{
	
	    	SQLQuery query = session.createSQLQuery(sql);
	    	query.setReadOnly(true);
	    	List<Object[]> rows = query.list();
	    	// iterate over results
	    	for (int i = 0; i < rows.size(); i++) {
	    		Object[] row =rows.get(i);
//	    			System.out.println("row[0] ="+row[0] +" ,row[1] ="+row[1]);
	    		int maleC=0 , femaleC=0;
	    		if(row[1]!=null){
	    			maleC =(Integer)row[1];
	    		}
	    		if(row[2]!=null){
	    			femaleC =(Integer)row[2];
	    		}
	    			if(reportChart1.containsKey((String)row[0])){
	    				String ss=reportChart1.get((String)row[0]);
	    				String[] sv=ss.split(" = ");
	    				int mc =Integer.parseInt(sv[0]);
	    				int fc =Integer.parseInt(sv[1]);
	    				mc = mc +maleC;
	    				fc = fc +femaleC;
	    				reportChart1.put((String)row[0], mc+" = "+fc);
	    			}else{
	    				reportChart1.put((String)row[0], maleC+" = "+femaleC);
	    			}
	    		
	    	}	
    	tx.commit();
		session.close();
		}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
		}
		return reportChart1;
	}
	
	public Map<String , Integer> getMTC_TotalAdmFor20_10Beds(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Map<String,Integer> reportChart1=new LinkedHashMap();
		String sql = "select f.mtc_name , f.total_admissions from "        
		+" final_report f LEFT OUTER JOIN mtc m on "
		+ " m.mtc_id=f.mtc_type_id  LEFT OUTER JOIN block b on m.block_type_id = b.block_id " 
		+" LEFT OUTER JOIN district d on d.district_id =b.district_type_id "
		+ " where (m.no_beds =20) order by total_admissions DESC NULLS LAST ";        
		try{
	
	    	SQLQuery query = session.createSQLQuery(sql);
	    	query.setReadOnly(true);
	    	List<Object[]> rows = query.list();
	    	// iterate over results
	    	for (int i = 0; i < rows.size(); i++) {
	    		for(Object[] row : rows){
//	    			System.out.println("row[0] ="+row[0] +" ,row[1] ="+row[1]);
	    			reportChart1.put((String)row[0], (Integer)row[1]);
	    		}
	    	}	
    	tx.commit();
		session.close();
		}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
		}
		return reportChart1;
	}

    public List<SearchData> searchListBySamId(String samId){
    	
    	List<SearchData> resultValues=new ArrayList<>();
    	Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select a.sam_id, c.child_name , to_char(r.registration_date, 'DD Mon YYYY HH24:MI:SS') as r_date,"
				+ " to_char(a.admission_date, 'DD Mon YYYY HH24:MI:SS') as a_date"
				+ " , c.father_name , m.mtc_name "
				+ " from registration r LEFT OUTER JOIN admission a on r.registration_id=a.registration_type_id   "
				+ " LEFT OUTER JOIN child c on r.child_type_id=c.child_id LEFT OUTER JOIN mtc m on m.mtc_id = r.mtc_type_id"
				+ " where a.sam_id='"+samId+"' order by mtc_name asc ";
		try{
	    	SQLQuery query = session.createSQLQuery(sql);
	    	query.setReadOnly(true);
	    	List<Object[]> rows = query.list();
	    	for (int i = 0; i < rows.size(); i++) {
	    		SearchData  searchData = new SearchData();
	    		Object[] row =rows.get(i);
	    		searchData.setSamId((String) row[0]);
	    		searchData.setChildName((String) row[1]);
	    		searchData.setRegistrationDate( (String) row[2]);
	    		searchData.setAdmissionDate((String) row[3]);
	    		searchData.setFatherName((String) row[4]);
	    		searchData.setMtcName((String) row[5]);
//	    		searchData.setStatus(status);
	    		resultValues.add(searchData);
	    	}	
    	tx.commit();
		session.close();
		}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
		}
		
    	return resultValues;
    }
}
