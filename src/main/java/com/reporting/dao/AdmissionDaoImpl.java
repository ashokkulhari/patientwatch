package com.reporting.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.Admission;

public class AdmissionDaoImpl implements AdmissionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveAdmission(Admission admission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		if(admission!=null){
			try{
				session.save(admission);
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
	public Admission findAdmissionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
