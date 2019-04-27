package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerResponseDto;

public interface CustomerService {
	
	String createCustomer(CustomerDto dto);
	
	List<CustomerResponseDto> getAllCustomers();
	
	CustomerDto updateCustomer( int customerId, CustomerDto dto);

}
