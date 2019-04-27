package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.GoldProducts;

public interface GoldProductRepository {

	List<GoldProducts> findProductByCategoryId(long gold_prdt_id);

	List<GoldProducts> getAllProducts();

}
