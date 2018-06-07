package com.reporting.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.State;

public class StateDaoImpl implements StateDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveState(State state) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<State> findAllStates() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<State> states=null;
		try{
			states  = (List<State>) session.createQuery("from state order by state_name ASC").list();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return states;
	}

}
