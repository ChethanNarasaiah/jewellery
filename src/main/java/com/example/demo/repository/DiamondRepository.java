package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.DiamondCategories;

public interface DiamondRepository {

	List<DiamondCategories> getAllCategory();

	DiamondCategories getCategoryById(int categoryId);

}
