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
import com.example.demo.model.DiamondCategories;
import com.example.demo.repository.DiamondRepository;
import com.example.demo.service.DiamondService;

@Service
public class DiamondServiceImpl implements DiamondService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DiamondRepository diamondRepository;

	@Override
	public String createDiamondCategories(CategoryDto categoryDto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		DiamondCategories category = new DiamondCategories();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryCreatedDate(new Date());
		category.setCategoryUpdatedDate(new Date());
		Integer result = (Integer) session.save(category);
		transaction.commit();
		session.close();
		return "Inserted " + result + " Diamond Category Sucessfully.";
	}

	@Override
	public List<CategoryReponseDto> getAllDiamondCategories() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<DiamondCategories> categoryList = diamondRepository.getAllCategory();
		List<CategoryReponseDto> categoryResponseList = new ArrayList<CategoryReponseDto>();
		ListIterator<DiamondCategories> iterator = categoryList.listIterator();
		while (iterator.hasNext()) {
			DiamondCategories category = (DiamondCategories) iterator.next();
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
