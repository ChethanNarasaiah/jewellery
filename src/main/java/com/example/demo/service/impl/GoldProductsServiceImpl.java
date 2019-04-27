package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.InvalidQuantityException;
import com.example.demo.exceptions.ProductCreationException;
import com.example.demo.model.GoldCategories;
import com.example.demo.model.GoldProducts;
import com.example.demo.repository.GoldCategoriesRepository;
import com.example.demo.repository.GoldProductRepository;
import com.example.demo.service.GoldProductsService;

@Service
public class GoldProductsServiceImpl implements GoldProductsService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GoldProductRepository goldProductRepository;

	@Autowired
	private GoldCategoriesRepository goldCategoryRepository;

	@Override
	public String createProduct(ProductDto dao) {
		if (dao.getQuantity() <= 0 || dao.getCategoryId() == 0) {
			throw new ProductCreationException("Product Creation Failed");
		}
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		GoldCategories category = goldCategoryRepository.getCategoryById(dao.getCategoryId());
		if (category == null) {
			throw new CategoryNotFoundException("Category Not Created");
		}
		GoldProducts product = new GoldProducts();
		product.setImageName(dao.getImageName());
		product.setProductName(dao.getProductName());
		product.setPrice(dao.getPrice());
		product.setWeightsInGram(dao.getWeightsInGram());
		product.setQuantity(dao.getQuantity());
		if (dao.getQuantity() == 0) {
			throw new InvalidQuantityException("Quantity Cannot Be 0 or null");
		}
		product.setProductCreatedDate(new Date());
		product.setProductUpdatedDate(new Date());
		product.setGoldCate(category);
		Long result = (Long) session.save(product);
		transaction.commit();
		session.close();
		return "Gold Product " + result + " added successfully";
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<GoldProducts> productList = goldProductRepository.getAllProducts();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<GoldProducts> iterator = productList.listIterator();
		while (iterator.hasNext()) {
			GoldProducts product = iterator.next();
			ProductResponseDto productDto = new ProductResponseDto();
			productDto.setId(product.getId());
			productDto.setProductName(product.getProductName());
			productDto.setPrice(product.getPrice());
			productDto.setWeightsInGram(product.getWeightsInGram());
			productResponseDto.add(productDto);
		}
		transaction.commit();
		session.close();
		return productResponseDto;
	}

	@Override
	public List<ProductResponseDto> getProductsByCatId(Integer cat_Id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String HQL_QUERY = "from GoldCategories d where d.id = :id";
		Query query = session.createQuery(HQL_QUERY);
		query.setParameter("id", cat_Id);
		List<GoldCategories> categoryList = query.getResultList();
		GoldCategories category = categoryList.get(0);
		List<GoldProducts> products = category.getGoldProduct();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<GoldProducts> iterator = products.listIterator();
		while (iterator.hasNext()) {
			GoldProducts product = iterator.next();
			ProductResponseDto productDto = new ProductResponseDto();
			productDto.setId(product.getId());
			productDto.setProductName(product.getProductName());
			productDto.setPrice(product.getPrice());
			productDto.setWeightsInGram(product.getWeightsInGram());
			productResponseDto.add(productDto);
		}
		transaction.commit();
		session.close();
		return productResponseDto;
	}

}
