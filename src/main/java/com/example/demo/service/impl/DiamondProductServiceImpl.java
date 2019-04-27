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
import com.example.demo.model.DiamondCategories;
import com.example.demo.model.DiamondProducts;
import com.example.demo.model.PlatinumProducts;
import com.example.demo.repository.DiamondProductRepository;
import com.example.demo.repository.DiamondRepository;
import com.example.demo.service.DiamondProductService;

@Service
public class DiamondProductServiceImpl implements DiamondProductService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DiamondRepository diamondRepository;

	@Autowired
	private DiamondProductRepository diamondProductRepository;

	@Override
	public String createProduct(ProductDto dao) {
		if (dao.getQuantity() <= 0 || dao.getCategoryId() == 0) {
			throw new ProductCreationException("Product Creation Failed");
		}
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		DiamondCategories category = diamondRepository.getCategoryById(dao.getCategoryId());
		if (category == null) {
			throw new CategoryNotFoundException("Category Not Created");
		}
		DiamondProducts product = new DiamondProducts();
		product.setProductName(dao.getProductName());
		product.setPrice(dao.getPrice());
		product.setWeightsInGram(dao.getWeightsInGram());
		product.setQuantity(dao.getQuantity());
		if (dao.getQuantity() == 0) {
			throw new InvalidQuantityException("Quantity Cannot Be 0 or null");
		}
		product.setProductCreatedDate(new Date());
		product.setProductUpdatedDate(new Date());
		product.setDiamondCate(category);
		Long result = (Long) session.save(product);
		transaction.commit();
		session.close();
		return "Gold Product " + result + " added successfully";
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<DiamondProducts> productList = diamondProductRepository.getAllProducts();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<DiamondProducts> iterator = productList.listIterator();
		while (iterator.hasNext()) {
			DiamondProducts product = iterator.next();
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
		String HQL_QUERY = "from DiamondCategories d where d.id = :id";
		Query query = session.createQuery(HQL_QUERY);
		query.setParameter("id", cat_Id);
		List<DiamondCategories> categoryList = query.getResultList();
		DiamondCategories category = categoryList.get(0);
		System.out.println("Output category is: " + category);
		List<DiamondProducts> products = category.getDiamondProduct();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<DiamondProducts> iterator = products.listIterator();
		while (iterator.hasNext()) {
			DiamondProducts product = iterator.next();
			ProductResponseDto productDto = new ProductResponseDto();
			productDto.setId(product.getId());
			productDto.setProductName(product.getProductName());
			productDto.setPrice(product.getPrice());
			productDto.setWeightsInGram(product.getWeightsInGram());
			System.out.println(product);
			productResponseDto.add(productDto);
		}
		transaction.commit();
		session.close();
		return productResponseDto;
	}

}
