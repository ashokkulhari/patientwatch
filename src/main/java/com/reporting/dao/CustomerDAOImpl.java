package com.reporting.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.model.Customer;
import com.reporting.model.DashboardData;
import com.reporting.util.Util;

public class CustomerDAOImpl implements CustomerDAO {

	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		if(customer!=null){
			try{
				session.save(customer);
				tx.commit();
				session.close();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
		}
	}

	@Override
	public Customer loginCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql ="from com.reporting.model.Customer as c where c.c_name=? and c.c_password=? ";
		try{
			Query query = session.createQuery(hql);
			query.setParameter(0, customer.getc_name());
			query.setParameter(1, customer.getc_password());
			customer=(Customer)query.uniqueResult();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return customer;
	}
	
	@Override
	public ArrayList<ArrayList<Object>> getData(String input,String district,String block,String mtc,String month,String noOfBeds,String quarter){
		
		String s="";
		s = Util.formFilters(district, block, mtc, month, noOfBeds, quarter, s);
		
		String query ="select f.districts,f.mtc_name ,"+input+" from final_report f LEFT OUTER JOIN mtc m on "
		+ " m.mtc_id=f.mtc_type_id  LEFT OUTER JOIN block b on m.block_type_id = b.block_id " 
		+" LEFT OUTER JOIN district d on d.district_id =b.district_type_id "
				+ s+" order by 1 asc , 2 asc ";
		if("".equals(s.trim())){
			query=query +" limit 200 ";
		}
		System.out.println("  Filtered : "+query);
    	return readReportData(query);
    }
	
	@Override
	public ArrayList<ArrayList<Object>> getData(String district,String block,String mtc,String month,String noOfBeds,String quarter){
		String str=" f.districts ,f.mtc_name ,f.total_admissions ,  f.male ,  f.female ,  f.v_0_6m ,  f.v_7_18m ,  f.v_19_36m ,"+
				"f.v_37_60m ,  f.g_60m ,  f.st ,  f.sc ,  f.obc ,  f.others ,  f.ptg ,  f.aww ,  f.asha ,  f.opd ,  f.own ,  f.others_ref ,"+  
				"f.wfh4 ,  f.wfh3 ,  f.wfh2 ,  f.wfh1 ,  f.z_score ,  f.muac ,  f.oedema ,  f.tabd ,  f.tbo ,  f.tvb ,  f.bor ,  f.v_less_5d ,  f.v_5_10d ,"+ 
				"f.v_11_15d ,  f.v_g_15d ,  f.als ,  f.t_exit ,f.discharge_r, f.wtg15 ,  f.awg ,  f.cured ,"+
				"f.recovery_r ,  f.p_recovered ,  f.p_recovered_r ,  f.non_respondents ,  f.non_respondent_r ,  f.defaulter ,  f.defaulter_r ,"+
				"f.lama ,  f.lama_r ,  f.up_referral ,  f.up_ref_r ,  f.death ,  f.death_r ,  f.relapse ,  f.death_dfp ,  f.appetite ,"+
				"f.f1 ,  f.f2 ,  f.f3 ,  f.f4 ,  f.tff ,  f.fd ,  f.fp ";
		
		String s="";
		s = Util.formFilters(district, block, mtc, month, noOfBeds, quarter, s);
		String query ="select "+str+" from final_report f LEFT OUTER JOIN mtc m on "
		+ " m.mtc_id=f.mtc_type_id  LEFT OUTER JOIN block b on m.block_type_id = b.block_id " 
		+" LEFT OUTER JOIN district d on d.district_id =b.district_type_id  "+s+" order by 1 asc , 2 asc ";
		if("".equals(s.trim())){
			query=query +" limit 200 ";
		}
    	return readReportData(query);
    }
	public ArrayList<ArrayList<Object>> readReportData(String queryStr) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		ArrayList<ArrayList<Object>> rowsout= new ArrayList<ArrayList<Object>>();
		try{
    	SQLQuery query = session.createSQLQuery(queryStr);
    	query.setReadOnly(true);
    	ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
    	List<Object[]> rows = query.list();
    	// iterate over results
    	for (int i = 0; i < rows.size(); i++) {
    		Object[] row = rows.get(i);
    		ArrayList<Object> data= new ArrayList<Object>();
    		for (int j = 0; j < row.length; j++) {
    			if(j<2){
    				String ob = (String) row[j];
    	    	    data.add(ob);
    			}else{
    				if(row[j] instanceof Integer){
    					Integer ob = (Integer) row[j];
        	    	    data.add(ob);
    				}else if(row[j] instanceof Double){
    					Double ob = (Double) row[j];
        	    	    data.add(ob);
    				}else{
    					data.add(row[j]);
    				}
    				
    			}
				
			}
    		rowsout.add(data);
		}
//    	List data1 = query.list();
//
//        for(Object object : data1)
//        {
//           Map row = (Map)object;
//           for (Iterator iterator = row.keySet().iterator(); iterator.hasNext();) {
//			String key = (String) iterator.next();
//			String value=(String) row.get(key);
//			System.out.print(key +"First Name: " + row.get(key)); 
//			data.add(value);
//		}
           
           
        
    	results.close();
    	tx.commit();
		session.close();
	}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
	}
    	return rowsout;
	}
	@Override
	public DashboardData getDashboadData(String districtName, String mtcName , String monthRange , String noOfBeds) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		DashboardData dashboardData=new DashboardData();
//		String sql="select sum(tabd) as tabd_s,sum(total_admissions)as total_admissions_s,"
//				+ "round(((NULLIF(sum(t_exit),0)*100)/sum(total_admissions)),2) as d_rate, "
//				+ "sum(t_exit) as t_exit_s,avg(recovery_r) as recovery_r_s,avg(bor) as bor_s,avg(als) as als_s,"
//				+ "avg(defaulter_r) as defaulter_r_s, "
//				+ "avg(death_r) as death_r_s,avg(awg) as awg_s,count(mtc_name) from final_report ";
		String sql="select sum(f.tabd) as tabd_s,sum(f.total_admissions)as total_admissions_s,"
				+ " round(((NULLIF(sum(f.t_exit),0)*100)/sum(f.total_admissions)),2) as d_rate, "  
				+" sum(f.t_exit) as t_exit_s,avg(f.recovery_r)*100 as recovery_r_s,avg(f.bor)*100 as bor_s,"
				+ " avg(f.als) as als_s, avg(f.defaulter_r)*100 as defaulter_r_s, "
				+" avg(f.death_r)*100 as death_r_s,avg(f.awg) as awg_s,count(f.mtc_name)  from final_report f "
				+ " LEFT OUTER JOIN mtc m on  m.mtc_id=f.mtc_type_id "
				+" LEFT OUTER JOIN block b on m.block_type_id = b.block_id  "
				+" LEFT OUTER JOIN district d on d.district_id =b.district_type_id ";
		
		try{
			
			if(noOfBeds!=null && !"".equals(noOfBeds.trim())){
				String o=" where m.no_beds in ( "+noOfBeds+" )";
				sql =sql+o;
			}
			
			if(monthRange!=null && !"".equals(monthRange.trim())){
				String o=" and lower(f.month) in ( "+monthRange+" )";
				sql =sql+o;
			}
			
			if(districtName!=null && !"".equals(districtName.trim())){
				sql =sql+" and lower(d.district_name) = '"+districtName.trim()+"' ";
				if(mtcName!=null && !"".equals(mtcName.trim())){
					sql =sql+" and lower(m.mtc_name) = '"+mtcName.trim()+"' ";
				}
			}
	System.out.println("sql ="+sql);		
    	SQLQuery query = session.createSQLQuery(sql);
    	query.setReadOnly(true);
    	ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
    	List<Object[]> rows = query.list();
    	// iterate over results
    	for (int i = 0; i < rows.size(); i++) {
//    		Object[] row = rows.get(i);
//    		System.out.println(row[i]);
//    		for (int j = 0; j < row.length; j++) {
//    			System.out.println("J :"+row[j]);
//    		}
    		for(Object[] row : rows){
    			System.out.println("row[0] ="+row[0] +" ,row[1] ="+row[1]);
    			if(row[0]!=null){dashboardData.setTabd( ((BigInteger) row[0]).intValue());
    			}else{dashboardData.setTabd(0);    				
    			}
        		if(row[1]!=null){dashboardData.setTotalAdmissions( ((BigInteger) row[1]).intValue());
    			}else{dashboardData.setTotalAdmissions(0);
    			}
        		if(row[2]!=null){dashboardData.setDischargeRate(Double.parseDouble(df2.format( ((BigDecimal) row[2]).doubleValue() )));
        		}else{dashboardData.setDischargeRate(0.0);
        		}
//        		dashboardData.setTotalExit(((BigInteger)row[3]).intValue());
        		if(row[4]!=null){dashboardData.setRecoveryR(Double.parseDouble(df2.format((Double) row[4] )));
    			}else{dashboardData.setRecoveryR(0.0);
    			}
        		
        		if(row[5]!=null){dashboardData.setBor(Double.parseDouble(df2.format( (Double) row[5])));
    			}else{dashboardData.setBor(0.0);
    			}
        		
        		if(row[6]!=null){dashboardData.setAls(Double.parseDouble(df2.format((Double) row[6])));
    			}else{dashboardData.setAls(0.0);
    			}
        		if(row[7]!=null){dashboardData.setDefaulterR(Double.parseDouble(df2.format( (Double) row[7] )));
    			}else{dashboardData.setDefaulterR(0.0);
    			}
        		if(row[8]!=null){dashboardData.setDeathR(Double.parseDouble(df2.format((Double) row[8] )));
    			}else{dashboardData.setDeathR(0.0);
    			}
        		if(row[9]!=null){dashboardData.setAwg(Double.parseDouble(df2.format((Double) row[9] )));
    			}else{dashboardData.setAwg(0.0);
    			}
        		
        		
    		}

		}
    	results.close();
    	tx.commit();
		session.close();
	}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
	}
		return dashboardData;
	}
}
