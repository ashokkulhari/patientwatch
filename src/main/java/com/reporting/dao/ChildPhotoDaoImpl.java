package com.reporting.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.ChildPhoto;

public class ChildPhotoDaoImpl implements ChildPhotoDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveChildPhoto(ChildPhoto childPhoto) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		if(childPhoto!=null){
			try{
				session.save(childPhoto);
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
	public ChildPhoto updateChildPhoto(ChildPhoto childPhto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildPhoto findChildPhotoByRegId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
