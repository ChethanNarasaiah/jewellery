package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryReponseDto;
import com.example.demo.model.PlatinumCategory;
import com.example.demo.repository.PlatinumCategoryRepository;
import com.example.demo.service.PlatinumCategoryService;

@Transactional
@Service
public class PlatinumCategoryServiceImpl implements PlatinumCategoryService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PlatinumCategoryRepository categoryRepository;

	@Override
	public String createCategory(CategoryDto categoryDto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		PlatinumCategory category = new PlatinumCategory();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryCreatedDate(new Date());
		category.setCategoryUpdatedDate(new Date());
		Integer result = (Integer) session.save(category);
		transaction.commit();
		session.close();
		return "Inserted " + result + " Platinum Category Sucessfully.";
	}

	@Override
	public List<CategoryReponseDto> getAllCategory() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<PlatinumCategory> categoryList = categoryRepository.getAllCategory();
		List<CategoryReponseDto> categoryResponseList = new ArrayList<CategoryReponseDto>();
		ListIterator<PlatinumCategory> iterator = categoryList.listIterator();
		while (iterator.hasNext()) {
			PlatinumCategory category = (PlatinumCategory) iterator.next();
			CategoryReponseDto categoryDto = new CategoryReponseDto();
			categoryDto.setCategoryId(category.getId());
			categoryDto.setCategoryName(category.getCategoryName());
			categoryResponseList.add(categoryDto);
		}
		transaction.commit();
		session.close();
		return categoryResponseList;
	}

	
}
