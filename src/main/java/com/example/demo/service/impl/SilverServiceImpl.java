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
import com.example.demo.model.SilverCategories;
import com.example.demo.repository.SilverRepository;
import com.example.demo.service.SilverService;

@Service
public class SilverServiceImpl implements SilverService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SilverRepository silverRepository;

	@Override
	public String createSilverCate(CategoryDto categoryDto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		SilverCategories category = new SilverCategories();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryCreatedDate(new Date());
		category.setCategoryUpdatedDate(new Date());
		Integer result = (Integer) session.save(category);
		transaction.commit();
		session.close();
		return "Inserted " + result + " Silver Category Sucessfully.";
	}

	@Override
	public List<CategoryReponseDto> getAllSilverCate() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<SilverCategories> categoryList = silverRepository.getAllCategory();
		List<CategoryReponseDto> categoryResponseList = new ArrayList<CategoryReponseDto>();
		ListIterator<SilverCategories> iterator = categoryList.listIterator();
		while (iterator.hasNext()) {
			SilverCategories category = (SilverCategories) iterator.next();
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
