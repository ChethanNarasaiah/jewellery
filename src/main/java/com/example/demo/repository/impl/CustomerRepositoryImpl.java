package com.example.demo.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Customer findCustomerById(int customerId) {
		Session session = sessionFactory.openSession();

		String query = "SELECT c FROM Customer c WHERE c.id=:id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", customerId);
		List<Customer> queryResult = createQuery.getResultList();

		Customer result;
		if (queryResult == null || queryResult.size() <= 0)
			return null;
		else
			result = queryResult.get(0);
		session.close();
		return result;

	}

	@Override
	public List<Customer> getAllCustomers() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String query = "SELECT c FROM Customer c ORDER BY c ASC";
		Query createQuery = session.createQuery(query);
		List<Customer> customers = createQuery.getResultList();
		transaction.commit();
		session.close();
		return customers;
	}

}
