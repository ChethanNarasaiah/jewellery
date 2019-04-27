package com.example.demo.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DiamondCategories;
import com.example.demo.repository.DiamondRepository;

@Repository
public class DiamondRepositoryImpl implements DiamondRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DiamondCategories> getAllCategory() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT DISTINCT d FROM DiamondCategories d ORDER BY d.id ASC";
		Query createQuery = session.createQuery(query);
		List<DiamondCategories> queryResult;
		queryResult = createQuery.getResultList();
		transaction.commit();
		session.close();
		return queryResult;
	}

	@Override
	public DiamondCategories getCategoryById(int categoryId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT c FROM DiamondCategories c WHERE c.id=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", categoryId);
		List<DiamondCategories> queryResult = createQuery.getResultList();
		DiamondCategories category;
		if (queryResult == null || queryResult.size() <= 0)
			return null;
		else
			category = queryResult.get(0);
		transaction.commit();
		session.close();
		return category;
	}

}
