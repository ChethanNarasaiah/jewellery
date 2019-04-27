package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryReponseDto;

public interface DiamondService {
	
	String createDiamondCategories(CategoryDto categoryDto);

	List<CategoryReponseDto> getAllDiamondCategories();

}
