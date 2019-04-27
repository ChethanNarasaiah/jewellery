package com.example.demo.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sales;
import com.example.demo.repository.SalesRepository;

@Repository
public class SalesRepositoryImpl implements SalesRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Sales> findSalesByProductId(long prduct_id) {
		Session session = sessionFactory.openSession();
		// String query = "SELECT Sales_id FROM Product product JOIN
		// product.sales sales WHERE product.id =: id ORDER BY sales.id desc ";
		/*
		 * String query =
		 * "SELECT Sales_id FROM Product p JOIN Sales s WHERE p.id =: id";
		 */
		String query = "SELECT s FROM Sales s WHERE s.product.id = :id";
		Query createQuery = session.createQuery(query);
		createQuery.setParameter("id", prduct_id);
		List<Sales> sales = createQuery.getResultList();
		Sales result = sales.get(0);
		System.out.println(result);
		session.close();
		return sales;
	}

	@Override
	public List<Sales> getSales(long prduct_id) {
		Session session = sessionFactory.openSession();
		String query = " SELECT *  FROM db_demo1.tb_sales s"
				+ "inner join db_demo1.tb_product_sales p on s.Sales_id = p.Sales_id"
				+ "inner join db_demo1.tb_products pd on p.prduct_id=pd.prduct_id where pd.prduct_id=?1";
		Query createQuery = session.createNativeQuery(query);
		createQuery.setParameter(1, prduct_id);
		List<Sales> sales = createQuery.getResultList();
		return sales;
	}

	@Override
	public List<Sales> getAllSales() {
		Session session = sessionFactory.openSession();
		String query = "SELECT s FROM Sales s ORDER BY s.id ASC";
		Query createQuery = session.createQuery(query);
//		createQuery.setParameter("id", sales_id);
		List<Sales> sales = createQuery.getResultList();
		session.close();
		return sales;
	}

}
