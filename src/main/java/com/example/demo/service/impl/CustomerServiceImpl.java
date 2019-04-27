package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerResponseDto;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String createCustomer(CustomerDto dto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer();
		customer.setCustomerName(dto.getCustomerName());
		customer.setDateOfPurchase(new Date());
		customer.setPhoneNumber(dto.getPhoneNumber());
		customer.setCostOfPurchase(dto.getCostOfPurchase());
		customer.setAddress(dto.getAddress());
		session.save(customer);
		transaction.commit();
		session.close();
		return "New Customer added";
	}

	@Override
	public List<CustomerResponseDto> getAllCustomers() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Customer> customers = customerRepository.getAllCustomers();
		List<CustomerResponseDto> customerList = new ArrayList<>();
		Iterator<Customer> iterator = customers.iterator();
		while (iterator.hasNext()) {
			Customer customer = iterator.next();
			CustomerResponseDto responseDto = new CustomerResponseDto();
			responseDto.setId(customer.getId());
			responseDto.setCustomerName(customer.getCustomerName());
			responseDto.setPhoneNumber(customer.getPhoneNumber());
			responseDto.setDateOfPurchase(customer.getDateOfPurchase());
			responseDto.setCostOfPurchase(customer.getCostOfPurchase());
			responseDto.setAddress(customer.getAddress());
			customerList.add(responseDto);
		}
		return customerList;
	}

	@Override
	public CustomerDto updateCustomer(int customerId, CustomerDto dto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String HQL_QUERY = "from Customer customer where customer.id = :customerId";
		Query query = session.createQuery(HQL_QUERY);
		query.setParameter("customerId", customerId);
		List<Customer> customer = query.getResultList();
		Customer cust = customer.get(0);
		cust.setCustomerName(dto.getCustomerName());
		cust.setPhoneNumber(dto.getPhoneNumber());
		cust.setCostOfPurchase(dto.getCostOfPurchase());
		cust.setAddress(dto.getAddress());
		session.save(cust);
		session.getTransaction().commit();
		return dto;
	}
}
