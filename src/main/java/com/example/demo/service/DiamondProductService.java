package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;

public interface DiamondProductService {

	String createProduct(ProductDto dao);

	List<ProductResponseDto> getAllProducts();

	List<ProductResponseDto> getProductsByCatId(Integer cat_Id);
}
