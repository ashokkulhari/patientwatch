package com.reporting.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.District;

public class DistrictDaoImpl implements DistrictDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveDistrict(District district) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<District> findAllDistricts() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<District> districts=null;
		String hql ="from com.reporting.entity.District as d order by d.district_name ASC ";
		try{
			
			districts  = (List<District>) session.createQuery(hql).list();
//			Query query = session.createQuery(hql);
//			List<Object[]> rows = query.list();
//			for (int i = 0; i <rows.size(); i++) {
//				Object[] row = rows.get(i);
//				for (int j = 0; j < row.length; j++) {
//					System.out.println(row[j]);
//				}
//			}
			
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return districts;
	}
	@Override
	public List<String> findAllDistrictNames() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<String> districts=null;
		String hql ="select d.district_name from com.reporting.entity.District as d order by d.district_name ASC ";
		try{
			Query qry = session.createQuery(hql);
			districts =(List<String>)qry.list();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return districts;
	}
	@Override
	public Map<String, List<String>> findAllLocationNames() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Map<String, List<String>> mapvalues=new LinkedHashMap<>();
		List<String> values;
		String queryStr ="select d.district_name , b.block_name,m.mtc_name from "
				+ "district d LEFT OUTER JOIN block b on d.district_id =b.district_type_id LEFT OUTER JOIN mtc m "+
				" on m.block_type_id = b.block_id order by 1 ASC,2 ASC,3 ASC;";
		try{
			SQLQuery query = session.createSQLQuery(queryStr);
			List<Object[]> rows = query.list();
			for(Object[] row : rows){
				String district = (String) row[0];
				String block = (String) row[1];
				String mtc = (String) row[2];
				
				if(mapvalues.containsKey(district)){
					values=mapvalues.get(district);
					if(!values.contains(block)){
						values.add(block);
						mapvalues.put(district, values);
					}
				}else{
					values=new ArrayList<>();
					values.add(block);
					mapvalues.put(district, values);
				}
				if(mtc!=null && !"".equals(mtc.trim())){
					if(mapvalues.containsKey(block)){
						values=mapvalues.get(block);
						if(!values.contains(mtc)){
							values.add(mtc);
							mapvalues.put(block, values);
						}
					}else{values=new ArrayList<>();
						values=new ArrayList<>();
						values.add(mtc);
						mapvalues.put(block, values);
					}
				}
				
				
			}
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return mapvalues;
	}

}
