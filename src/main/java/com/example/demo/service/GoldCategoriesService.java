package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryReponseDto;

public interface GoldCategoriesService {

	String createGoldCategories(CategoryDto categoryDto);

	List<CategoryReponseDto> getAllGoldCategories();

}
