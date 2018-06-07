package com.reporting.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.Admission;
import com.reporting.entity.AnthropometryOne;
import com.reporting.entity.Block;
import com.reporting.entity.Child;
import com.reporting.entity.ChildPhoto;
import com.reporting.entity.MTC;
import com.reporting.entity.Registration;

public class RegistrationDaoImpl implements RegistrationDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public void saveRegistration(Registration registration) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		if(registration!=null){
			try{
				session.save(registration);
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
	public Registration findRegistrationById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findAllRegistrationViewData(String samId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	/*	String sql = "select * from registration r  " +
					" LEFT OUTER JOIN  admission a on a.registration_type_id = r.registration_id " +
					" LEFT OUTER JOIN child c on r.child_type_id = c.child_id " +
					" LEFT OUTER JOIN child_pic p on p.registration_type_id=r.registration_id " +
					" LEFT OUTER JOIN mtc m on m.mtc_id = r.mtc_type_id " +
					" LEFT OUTER JOIN block b on m.block_type_id = b.block_id " +
					" LEFT OUTER JOIN district d on b.district_type_id = d.district_id " +
					" where a.sam_id='1-01-002-00078'";    
		*/
		String sql ="select * from admission a   "+
					" LEFT OUTER JOIN child_pic p on p.registration_type_id=a.registration_type_id "+
					" LEFT OUTER JOIN anthropometry_one ao on ao.registration_type_id=a.registration_type_id "+
					" where a.sam_id='"+samId+"'  order by ao.entry_type_id asc ";
		List<Object> values = new ArrayList<>();
		try{
	
	    	SQLQuery query = session.createSQLQuery(sql);
	    	query.addEntity("a",Admission.class);
	    	query.addEntity("p",ChildPhoto.class);
	    	query.addEntity("ao",AnthropometryOne.class);
	    	
	    	query.setReadOnly(true);
	        List<Object[]> rows = query.list();
	    	System.out.println("rows size "+rows.size());
	    	for (int i = 0; i < rows.size(); i++) {
	    		List<Object> l = new ArrayList<>();
	    		Object[] row =rows.get(i);
	    		l.add(row[0]);
    			l.add(row[1]);
    			l.add(row[2]);
	    		values.add(l);
	    	}	
	    	
    	tx.commit();
		session.close();
		}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
		}
		return values;
	}

	

}
