package com.reporting.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.MTC;
import com.reporting.model.Customer;

public class MTCDaoImpl implements MTCDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveMTC(MTC mtc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MTC> findAllMTCs() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<MTC> mtcs=null;
		try{
			mtcs  = (List<MTC>) session.createQuery("from com.reporting.entity.MTC m order by m.mtc_name ASC").list();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return mtcs;
	}
	@Override
	public List<String> findAllMTCNames() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<String> mtcs=null;
		String hql ="select m.mtc_name from com.reporting.entity.MTC as m order by m.mtc_name ASC ";
		try{
			Query qry = session.createQuery(hql);
			mtcs =(List<String>)qry.list();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return mtcs;
	}
	@Override
	public MTC findAllMTCByNames(String mtcName) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		MTC mtc=null;
		try{
			Query query = session.createQuery("from com.reporting.entity.MTC m where m.mtc_name =? ");
			query.setParameter(0, mtcName);
			mtc=(MTC)query.uniqueResult();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return mtc;
	}

}
