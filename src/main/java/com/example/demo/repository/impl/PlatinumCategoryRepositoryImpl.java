package com.example.demo.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PlatinumCategory;
import com.example.demo.repository.PlatinumCategoryRepository;

@Repository
public class PlatinumCategoryRepositoryImpl implements PlatinumCategoryRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PlatinumCategory getCategoryById(int categoryId) {
		Session session = sessionFactory.openSession();
		String query = "SELECT c FROM PlatinumCategory c WHERE c.id=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", categoryId);
		List<PlatinumCategory> queryResult = createQuery.getResultList();
		PlatinumCategory category;
		if (queryResult == null || queryResult.size() <= 0)
			return null;
		else
		category= queryResult.get(0);
		session.close();
		return category;
	}

	@Override
	public List<PlatinumCategory> getAllCategory() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT DISTINCT c FROM PlatinumCategory c ORDER BY c.id ASC";
		Query createQuery = session.createQuery(query);
		List<PlatinumCategory> queryResult = createQuery.getResultList();
		transaction.commit();
		session.close();
		return queryResult;
	}

}
