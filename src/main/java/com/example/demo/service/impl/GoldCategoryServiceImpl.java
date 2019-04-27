package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryReponseDto;
import com.example.demo.model.GoldCategories;
import com.example.demo.repository.GoldCategoriesRepository;
import com.example.demo.service.GoldCategoriesService;

@Service
public class GoldCategoryServiceImpl implements GoldCategoriesService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GoldCategoriesRepository goldCategoriesRepository;

	@Override
	public String createGoldCategories(CategoryDto categoryDto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		GoldCategories category = new GoldCategories();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryCreatedDate(new Date());
		category.setCategoryUpdatedDate(new Date());
		Integer result = (Integer) session.save(category);
		transaction.commit();
		session.close();
		return "Inserted " + result + " Gold Category Sucessful.";

	}

	@Override
	public List<CategoryReponseDto> getAllGoldCategories() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<GoldCategories> categoryList = goldCategoriesRepository.getAllCategory();
		List<CategoryReponseDto> categoryResponseList = new ArrayList<CategoryReponseDto>();
		ListIterator<GoldCategories> iterator = categoryList.listIterator();
		while (iterator.hasNext()) {
			GoldCategories category = (GoldCategories) iterator.next();
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
