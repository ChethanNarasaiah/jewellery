package com.example.demo.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SilverProducts;
import com.example.demo.repository.SilverProductRepository;

@Repository
public class SilverProductRepositoryImpl implements SilverProductRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<SilverProducts> getAllProducts() {
		Session session = sessionFactory.openSession();
		String query = "SELECT DISTINCT p FROM SilverProducts p ORDER BY p.id ASC";
		Query createQuery = session.createQuery(query);
		List<SilverProducts> result = createQuery.getResultList();
		session.close();
		return result;
	}

	

}
