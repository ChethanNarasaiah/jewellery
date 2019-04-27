package com.example.demo.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PlatinumProducts;
import com.example.demo.repository.PlatinumProductRepository;

@Repository
public class PlatinumProductRepositoryImpl implements PlatinumProductRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PlatinumProducts findProductById(long productId) {

		Session session = sessionFactory.openSession();
		// String query = "SELECT p FROM Product p WHERE p.id IN (:id)";
		String query = "SELECT p FROM PlatinumProducts p WHERE p.id=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", productId);
		List<PlatinumProducts> queryResult = createQuery.getResultList();
		PlatinumProducts result;
		if (queryResult == null || queryResult.size() <= 0)
			return null;
		else
			result = queryResult.get(0);
		session.close();
		return result;
	}

	@Override
	public List<PlatinumProducts> getAllProducts() {
		Session session = sessionFactory.openSession();
		String query = "SELECT DISTINCT p FROM PlatinumProducts p ORDER BY p.id ASC";
		Query createQuery = session.createQuery(query);
		List<PlatinumProducts> result = createQuery.getResultList();
		session.close();
		return result;
	}

	@Override
	public void updateQuantity(int quantity, long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "UPDATE PlatinumProducts p SET quantity=:quantity WHERE p.id=:id ";
		Query createQuery = session.createQuery(hql);
		createQuery.setParameter("quantity", quantity);
		createQuery.setParameter("id", id);
		createQuery.executeUpdate();
		transaction.commit();
		session.close();
	}

	@Override
	public PlatinumProducts getDateByPrdtId(int prduct_id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT p FROM PlatinumProducts p WHERE p.id=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", prduct_id);
		List<PlatinumProducts> queryResult = createQuery.getResultList();
		PlatinumProducts product = queryResult.get(0);
		transaction.commit();
		session.close();
		return product;
	}

	@Override
	public List<PlatinumProducts> getProductsByPage() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT DISTINCT p FROM PlatinumProducts p ORDER BY p.id ASC";
		Query createQuery = session.createQuery(query);
		createQuery.setFirstResult(0);
		createQuery.setMaxResults(6);
		List<PlatinumProducts> result;
		result = (List<PlatinumProducts>) createQuery.list();
		return result;
	}

	@Override
	public List<PlatinumProducts> getCategorytByCatId(int cat_id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT c FROM  PlatinumProducts c WHERE p.platinumCategory=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", cat_id);
		List<PlatinumProducts> queryResult = createQuery.getResultList();
		System.out.println(queryResult);
		return queryResult;

	}

}
