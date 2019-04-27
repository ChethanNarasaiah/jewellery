package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.PlatinumProducts;

public interface PlatinumProductRepository {

	PlatinumProducts findProductById(long productId);

	List<PlatinumProducts> getAllProducts();

	void updateQuantity(int quantity, long id);

	PlatinumProducts getDateByPrdtId(int prduct_id);

	List<PlatinumProducts> getProductsByPage();

	List<PlatinumProducts> getCategorytByCatId(int cat_id);

	
}