package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryReponseDto;

public interface PlatinumCategoryService {

	String createCategory(CategoryDto categoryDto);

	List<CategoryReponseDto> getAllCategory();

}
