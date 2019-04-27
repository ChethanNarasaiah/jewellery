package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;

public interface PlatinumProductService {

	String createProduct(ProductDto dao);

	List<ProductResponseDto> getAllProducts();

	Date getDate(int prduct_id);

	ProductDto updateProduct(int productId, ProductDto dto);

	List<ProductResponseDto> getProductsByPage();

	List<ProductResponseDto> getProductsByCatId(int cat_Id);

}
