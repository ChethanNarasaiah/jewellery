package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Customer;

public interface CustomerRepository {

	Customer findCustomerById(int customerId);
	
	List<Customer> getAllCustomers();

}
