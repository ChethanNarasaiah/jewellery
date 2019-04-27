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

import com.example.demo.dto.SalesDto;
import com.example.demo.dto.SalesResponseDto;
import com.example.demo.dto.SuccessMessageDto;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.service.SalesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/sales")
@Api(value = "sales")
public class SalesController {
	
	@Autowired
	private SalesService salesService;
	
	@ApiOperation(value = "/post", notes = "This api is used to add Sales", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@PostMapping("/newSales")
	public ResponseEntity<?> addSales(@RequestBody SalesDto salesDto ) throws ProductNotFoundException{
		return new ResponseEntity<SuccessMessageDto>(new SuccessMessageDto(salesService.createSales(salesDto)), HttpStatus.OK);
	}
	
	/*@ApiOperation(value = "/getDateByPrdtId", notes = "This api is used to get Product Date", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/prduct_id/{id}")
	public ResponseEntity<?> getProductDate(@PathVariable("id") long prduct_id){
		return new ResponseEntity<Date>(salesService.getDate(prduct_id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "/getSalesDateByPrdtId", notes = "This api is used to get Sales Date", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getSales/prduct_id/{id}")
	public ResponseEntity<?> getSalesDate(@PathVariable("id") long prduct_id){
		return new ResponseEntity<SalesResponseDto>(salesService.getSalesDate(prduct_id), HttpStatus.OK);
	}*/

	@ApiOperation(value = "/getAllSales", notes = "This api is used to get Sales", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getAllSales")
	public ResponseEntity<?> getAllSales(){
		return new ResponseEntity<List<SalesResponseDto>>(salesService.getAllSales(), HttpStatus.OK);
	}
}
