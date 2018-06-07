package com.reporting.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.AnthropometryDaily;
import com.reporting.model.Customer;

public class AnthropometryDailyDaoImpl implements AnthropometryDailyDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public void saveAnthropometryDaily(AnthropometryDaily anthropometryDaily) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		if(anthropometryDaily!=null){
			try{
				session.save(anthropometryDaily);
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
	public List<AnthropometryDaily> findAllForAdmission(int admissionId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<AnthropometryDaily> values =null;
		String hql ="from com.reporting.entity.AnthropometryDaily as ad  "
				+ " where ad.admission.admissionId=? order by ad.day asc ";
		try{
			Query query = session.createQuery(hql);
			query.setParameter(0, admissionId);
			values=query.list();
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
