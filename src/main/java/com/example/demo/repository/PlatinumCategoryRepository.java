package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.PlatinumCategory;

public interface PlatinumCategoryRepository {

	PlatinumCategory getCategoryById(int categoryId);

	List<PlatinumCategory> getAllCategory();
	
}
