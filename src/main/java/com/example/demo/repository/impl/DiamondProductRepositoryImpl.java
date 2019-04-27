package com.example.demo.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DiamondProducts;
import com.example.demo.repository.DiamondProductRepository;

@Repository
public class DiamondProductRepositoryImpl implements DiamondProductRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DiamondProducts> getAllProducts() {
		Session session = sessionFactory.openSession();
		String query = "SELECT DISTINCT p FROM DiamondProducts p ORDER BY p.id ASC";
		Query createQuery = session.createQuery(query);
		List<DiamondProducts> result = createQuery.getResultList();
		session.close();
		return result;
	}

}
