package com.reporting.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.Child;

public class ChildDaoImpl implements ChildDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveChild(Child child) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		if(child!=null){
			try{
				session.save(child);
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
	public Child updateChild(Child child) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Child findChildById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Child findChildByNameChild(String childName) {
		// TODO Auto-generated method stub
		return null;
	}

}
