package com.example.demo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.dto.SuccessMessageDto;
import com.example.demo.service.GoldProductsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/gold")
@Api(value = "Gold")
public class GoldProductController {

	@Autowired 
	private GoldProductsService goldProductsService;
	
	@ApiOperation(value = "/post", notes = "This api is used to add Products", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@PostMapping("/addGoldProduct")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto dto) {
		return new ResponseEntity<SuccessMessageDto>(new SuccessMessageDto(goldProductsService.createProduct(dto)),
				HttpStatus.CREATED);
	}

	@ApiOperation(value = "/get", notes = "This api is used to get all Products", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getGoldProducts")
	public ResponseEntity<?> getProducts() {
		return new ResponseEntity<List<ProductResponseDto>>(goldProductsService.getAllProducts(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "/get", notes = "This api is used to get all Products", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getProductsByCatId/{Cat_Id}")
	public ResponseEntity<?> getProducts(@PathVariable Integer Cat_Id) {
		return new ResponseEntity<List<ProductResponseDto>>(goldProductsService.getProductsByCatId(Cat_Id), HttpStatus.OK);
	}
}
