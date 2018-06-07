package com.reporting.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.Country;
import com.reporting.model.Customer;

public class CountryDaoImpl implements CountryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public void saveCountry(Country country) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Country> findAllCountries() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Country> countries=null;
		try{
			countries  = (List<Country>) session.createQuery("from country order by country_name ASC").list();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return countries;
	}

	
}
