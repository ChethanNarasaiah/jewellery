package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryReponseDto;

public interface SilverService {

	String createSilverCate(CategoryDto categoryDto);

	List<CategoryReponseDto> getAllSilverCate();
}
