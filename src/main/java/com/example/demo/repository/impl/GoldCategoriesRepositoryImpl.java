package com.example.demo.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GoldCategories;
import com.example.demo.repository.GoldCategoriesRepository;

@Repository
public class GoldCategoriesRepositoryImpl implements GoldCategoriesRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public GoldCategories getCategoryById(int categoryId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT c FROM GoldCategories c WHERE c.id=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", categoryId);
		List<GoldCategories> queryResult = createQuery.getResultList();
		GoldCategories category;
		if (queryResult == null || queryResult.size() <= 0)
			return null;
		else
			category = queryResult.get(0);
		transaction.commit();
		session.close();
		return category;
	}

	@Override
	public List<GoldCategories> getAllCategory() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT DISTINCT c FROM GoldCategories c ORDER BY c.id ASC";
		Query createQuery = session.createQuery(query);
		List<GoldCategories> queryResult = createQuery.getResultList();
		transaction.commit();
		session.close();
		return queryResult;
	}

}
