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
import com.example.demo.model.SilverCategories;
import com.example.demo.model.SilverProducts;
import com.example.demo.repository.SilverProductRepository;
import com.example.demo.repository.SilverRepository;
import com.example.demo.service.SilverProductService;

@Service
public class SilverProductServiceImpl implements SilverProductService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SilverProductRepository silverProductRepository;

	@Autowired
	private SilverRepository silverCategoriesRepository;

	@Override
	public String createProduct(ProductDto dao) {
		if (dao.getQuantity() <= 0 || dao.getCategoryId() == 0) {
			throw new ProductCreationException("Product Creation Failed");
		}
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		SilverCategories category = silverCategoriesRepository.getCategoryById(dao.getCategoryId());
		if (category == null) {
			throw new CategoryNotFoundException("Category Not Created");
		}
		SilverProducts product = new SilverProducts();
		product.setProductName(dao.getProductName());
		product.setPrice(dao.getPrice());
		product.setWeightsInGram(dao.getWeightsInGram());
		product.setQuantity(dao.getQuantity());
		if (dao.getQuantity() == 0) {
			throw new InvalidQuantityException("Quantity Cannot Be 0 or null");
		}
		product.setProductCreatedDate(new Date());
		product.setProductUpdatedDate(new Date());
		product.setSilverCate(category);
		Long result = (Long) session.save(product);
		transaction.commit();
		session.close();
		return "Silver Product " + result + " added successfully";
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<SilverProducts> productList = silverProductRepository.getAllProducts();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<SilverProducts> iterator = productList.listIterator();
		while (iterator.hasNext()) {
			SilverProducts product = iterator.next();
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
		String HQL_QUERY = "from SilverCategories d where d.id = :id";
		Query query = session.createQuery(HQL_QUERY);
		query.setParameter("id", cat_Id);
		List<SilverCategories> categoryList = query.getResultList();
		SilverCategories category = categoryList.get(0);
		List<SilverProducts> products = category.getSilverProduct();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<SilverProducts> iterator = products.listIterator();
		while (iterator.hasNext()) {
			SilverProducts product = iterator.next();
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
