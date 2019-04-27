package com.example.demo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryReponseDto;
import com.example.demo.dto.SuccessMessageDto;
import com.example.demo.service.PlatinumCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/category")
@Api(value = "Category API")
public class PlatinumCategoryController {

	@Autowired
	private PlatinumCategoryService categoryService;

	@ApiOperation(value = "/post", notes = "This api is used to add Categories", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@PostMapping("/createPlatCate")
	public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<SuccessMessageDto>(new SuccessMessageDto(categoryService.createCategory(categoryDto)),
				HttpStatus.CREATED);
	}

	@ApiOperation(value = "/getAll", notes = "This api is used to get all Products", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getPlatCategories")
	public ResponseEntity<?> getAllCategory() {
		return new ResponseEntity<List<CategoryReponseDto>>(categoryService.getAllCategory(), HttpStatus.OK);
	}
	
}
