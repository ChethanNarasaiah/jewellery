package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDetailsDto;
import com.example.demo.dto.SalesDto;
import com.example.demo.dto.SalesResponseDto;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.InvalidQuantityException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.exceptions.SalesException;
import com.example.demo.model.Customer;
import com.example.demo.model.PlatinumProducts;
import com.example.demo.model.Sales;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PlatinumProductRepository;
import com.example.demo.repository.SalesRepository;
import com.example.demo.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PlatinumProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SalesRepository salesRepository;

	@Override
	public String createSales(SalesDto salesDto) throws ProductNotFoundException {
		if (salesDto == null || salesDto.getProduct() == null || salesDto.getCustomerId() == 0) {
			throw new SalesException("Sales Creation Failure");
		}
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Set<Customer> customerSet = new HashSet<>();
		List<PlatinumProducts> productList = new ArrayList<>();
		double productPrice = 0;
		int totalQuantity = 0;
		for (ProductDetailsDto l : salesDto.getProduct()) {
			PlatinumProducts product = productRepository.findProductById(l.getProductId());
			if (product == null) {
				throw new ProductNotFoundException("Product UnAvailable");
			}
			int quantityDto = l.getQuantity();
			if (quantityDto <= 0) {
				throw new SalesException("Quantity cannot be zero. Please select a valid Quantity");
			}
			int quantity = product.getQuantity();
			if (quantity <= 0) {
				throw new ProductNotFoundException("Product UnAvailable");
			}
			if (quantity < quantityDto) {
				throw new InvalidQuantityException("Requested Quantity is greater than Available Quantity ");
			}

			productPrice = productPrice + (product.getPrice() * quantityDto);
			totalQuantity = totalQuantity + quantityDto;

			int newQuantity = quantity - quantityDto;

			product.setQuantity(newQuantity);
			productList.add(product);
		}
		Customer customer = customerRepository.findCustomerById(salesDto.getCustomerId());
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not registered");
		}
		customerSet.add(customer);
		Sales sales = new Sales();
		sales.setCreatedDate(new Date());
		sales.setProduct(productList);
		sales.setCustomer(customer);
		sales.setTotalCost(productPrice);
		sales.setQuantity(totalQuantity);
		try {
			session.save(sales);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("failed" + e.getMessage());
			transaction.rollback();
			throw new SalesException("Sales Creation Failure");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return "Sales Registered Successfully";
	}

	@Override
	public Date getDate(long prduct_id) {
		Session session = sessionFactory.openSession();
		List<Sales> sales = salesRepository.findSalesByProductId(prduct_id);
		Date result = ((Sales) sales).getCreatedDate();
		return result;
	}

	@Override
	public SalesResponseDto getSalesDate(long prduct_id) {
		Session session = sessionFactory.openSession();
		List<Sales> sales = salesRepository.getSales(prduct_id);
		ListIterator<Sales> itr = sales.listIterator();
		SalesResponseDto response = new SalesResponseDto();
		while (itr.hasNext()) {
			Sales sales2 = itr.next();
			response.setCreatedDate(sales2.getCreatedDate());
			response.setQuantity(sales2.getQuantity());
		}
		return response;
	}

	@Override
	public List<SalesResponseDto> getAllSales() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Sales> salesList = salesRepository.getAllSales();
		ListIterator<Sales> iterator = salesList.listIterator();
		List<SalesResponseDto> dto = new ArrayList<>();
		while (iterator.hasNext()) {
			Sales sales = iterator.next();
			SalesResponseDto response = new SalesResponseDto();
			response.setId(sales.getId());
			response.setCreatedDate(sales.getCreatedDate());
			response.setQuantity(sales.getQuantity());
			response.setTotalCost(sales.getTotalCost());
			dto.add(response);
		}
		transaction.commit();
		session.close();
		return dto;
	}

}
