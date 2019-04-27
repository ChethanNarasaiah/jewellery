package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Sales;

public interface SalesRepository {
	
	List<Sales> findSalesByProductId(long prduct_id);
	
	List<Sales> getSales(long prduct_id);
	
	List<Sales> getAllSales();

}
