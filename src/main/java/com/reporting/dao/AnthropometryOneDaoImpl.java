package com.reporting.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.AnthropometryOne;

public class AnthropometryOneDaoImpl implements AnthropometryOneDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveAnthropometryOne(AnthropometryOne anthropometryOne) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		if(anthropometryOne!=null){
			try{
				session.save(anthropometryOne);
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
	public AnthropometryOne updateAnthropometryOne(AnthropometryOne anthropometryOne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnthropometryOne findAnthropometryOneByRegId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AnthropometryOne findAnthropometryOneByRegIdAndEntry(int registrationId, int entryType) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		AnthropometryOne anthropometryOne = null;
		String hql ="from com.reporting.model.AnthropometryOne as a where a.registration_type_id=? and a.entry_type_id=? ";
		try{
			Query query = session.createQuery(hql);
			query.setParameter(0, registrationId);
			query.setParameter(1, entryType);
			anthropometryOne=(AnthropometryOne)query.uniqueResult();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return anthropometryOne;
	}

}
