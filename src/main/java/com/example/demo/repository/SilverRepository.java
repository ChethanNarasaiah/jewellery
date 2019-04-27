package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.SilverCategories;

public interface SilverRepository {

	List<SilverCategories> getAllCategory();

	SilverCategories getCategoryById(int categoryId);

}
