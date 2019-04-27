package com.example.demo.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SilverCategories;
import com.example.demo.repository.SilverRepository;

@Repository
public class SilverRepositoryImpl implements SilverRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<SilverCategories> getAllCategory() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT DISTINCT s FROM SilverCategories s ORDER BY s.id ASC";
		Query createQuery = session.createQuery(query);
		List<SilverCategories> queryResult = createQuery.getResultList();
		transaction.commit();
		session.close();
		return queryResult;
	}

	@Override
	public SilverCategories getCategoryById(int categoryId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT c FROM SilverCategories c WHERE c.id=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", categoryId);
		List<SilverCategories> queryResult = createQuery.getResultList();
		SilverCategories category;
		if (queryResult == null || queryResult.size() <= 0)
			return null;
		else
			category = queryResult.get(0);
		transaction.commit();
		session.close();
		return category;
	}

}
