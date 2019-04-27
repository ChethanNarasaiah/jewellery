package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.InvalidQuantityException;
import com.example.demo.exceptions.ProductCreationException;
import com.example.demo.model.PlatinumCategory;
import com.example.demo.model.PlatinumProducts;
import com.example.demo.repository.PlatinumCategoryRepository;
import com.example.demo.repository.PlatinumProductRepository;
import com.example.demo.service.PlatinumProductService;

@Transactional
@Service
public class PlatinumProductServiceImpl implements PlatinumProductService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PlatinumCategoryRepository categoryRepository;

	@Autowired
	private PlatinumProductRepository productRepository;

	@Override
	public String createProduct(ProductDto dao) {
		if (dao.getQuantity() <= 0 || dao.getCategoryId() == 0) {
			throw new ProductCreationException("Product Creation Failed");
		}
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		PlatinumCategory category = categoryRepository.getCategoryById(dao.getCategoryId());
		if (category == null) {
			throw new CategoryNotFoundException("Category Not Created");
		}
		PlatinumProducts product = new PlatinumProducts();
		product.setProductName(dao.getProductName());
		product.setPrice(dao.getPrice());
		product.setWeightsInGram(dao.getWeightsInGram());
		product.setQuantity(dao.getQuantity());
		if (dao.getQuantity() == 0) {
			throw new InvalidQuantityException("Quantity Cannot Be 0 or null");
		}
		product.setProductCreatedDate(new Date());
		product.setProductUpdatedDate(new Date());
		product.setPlatinumCategory(category);
		Integer result = (Integer) session.save(product);
		transaction.commit();
		session.close();
		return "Product " + result + " added successfully";
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<PlatinumProducts> productList = productRepository.getAllProducts();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<PlatinumProducts> iterator = productList.listIterator();
		while (iterator.hasNext()) {
			PlatinumProducts product = iterator.next();
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
	public Date getDate(int prduct_id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		PlatinumProducts product = productRepository.getDateByPrdtId(prduct_id);
		Date result = product.getProductCreatedDate();
		session.close();
		return result;
	}

	@Override
	public ProductDto updateProduct(int productId, ProductDto dto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String HQL_QUERY = "from Product p where p.id = :id";
		Query query = session.createQuery(HQL_QUERY);
		query.setParameter("id", productId);
		List<PlatinumProducts> product = query.getResultList();
		PlatinumProducts prod = product.get(0);
		prod.setProductName(dto.getProductName());
		session.save(prod);
		session.getTransaction().commit();
		return dto;
	}

	@Override
	public List<ProductResponseDto> getProductsByPage() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<PlatinumProducts> productList = productRepository.getProductsByPage();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<PlatinumProducts> iterator = productList.listIterator();
		while (iterator.hasNext()) {
			PlatinumProducts product = iterator.next();
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
	public List<ProductResponseDto> getProductsByCatId(int cat_Id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String HQL_QUERY = "from PlatinumCategory c where c.id = :id";
		Query query = session.createQuery(HQL_QUERY);
		query.setParameter("id", cat_Id);
		List<PlatinumCategory> categoryList = query.getResultList();
		PlatinumCategory category = categoryList.get(0);
		System.out.println("Output category is: " + category);
		List<PlatinumProducts> products = category.getPlatinumProduct();
		List<ProductResponseDto> productResponseDto = new ArrayList<ProductResponseDto>();
		ListIterator<PlatinumProducts> iterator = products.listIterator();
		while (iterator.hasNext()) {
			PlatinumProducts product = iterator.next();
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
