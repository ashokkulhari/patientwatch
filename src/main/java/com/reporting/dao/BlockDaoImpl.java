package com.reporting.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.reporting.entity.Block;
import com.reporting.entity.MTC;

public class BlockDaoImpl implements BlockDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void saveBlock(Block block) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Block> findAllBlocks() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Block> blocks=null;
		try{
			blocks  = (List<Block>) session.createQuery("from com.reporting.entity.Block b order by b.block_name ASC").list();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return blocks;
	}
	@Override
	public List<String> findAllBlockNames() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<String> blocks=null;
		String hql ="select b.block_name from com.reporting.entity.Block as b order by b.block_name ASC ";
		try{
			Query qry = session.createQuery(hql);
			blocks =(List<String>)qry.list();
			tx.commit();
			session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return blocks;
	}

}
