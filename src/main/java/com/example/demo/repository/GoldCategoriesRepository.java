package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.GoldCategories;

public interface GoldCategoriesRepository {

	GoldCategories getCategoryById(int categoryId);

	List<GoldCategories> getAllCategory();

}
