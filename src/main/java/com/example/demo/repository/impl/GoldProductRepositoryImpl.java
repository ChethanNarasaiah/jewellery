package com.example.demo.repository.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GoldProducts;
import com.example.demo.repository.GoldProductRepository;

@Repository
public class GoldProductRepositoryImpl implements GoldProductRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GoldProducts> getAllProducts() {
		Session session = sessionFactory.openSession();
		String query = "SELECT DISTINCT p FROM GoldProducts p ORDER BY p.id ASC";
		Query createQuery = session.createQuery(query);
		List<GoldProducts> result = createQuery.getResultList();
		session.close();
		return result;
	}

	@Override
	public List<GoldProducts> findProductByCategoryId(long gold_prdt_id) {
		Session session = sessionFactory.openSession();
		String query = "SELECT * FROM GoldProducts g WHERE g.id=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", gold_prdt_id);
		List<GoldProducts> products = createQuery.getResultList();
		session.close();
		return products;
	}

}
