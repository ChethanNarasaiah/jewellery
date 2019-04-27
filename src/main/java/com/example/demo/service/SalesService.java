package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.dto.SalesDto;
import com.example.demo.dto.SalesResponseDto;
import com.example.demo.exceptions.ProductNotFoundException;

public interface SalesService {
	
	String createSales(SalesDto salesDto) throws ProductNotFoundException;
	
	Date getDate(long prduct_id);
	
	SalesResponseDto getSalesDate(long prduct_id);
	
	List<SalesResponseDto> getAllSales();

}
