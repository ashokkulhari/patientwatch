package com.reporting.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.Zone;

public class ZoneDaoImpl implements ZoneDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveZone(Zone zone) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Zone> findAllZones() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Zone> zones=null;
		try{
			zones  = (List<Zone>) session.createQuery("from zone order by zone_name ASC").list();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return zones;
	}

}
