package com.example.demo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerResponseDto;
import com.example.demo.dto.SuccessMessageDto;
import com.example.demo.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/customers")
@Api(value = "Customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "/post", notes = "This api is used to add new Customer", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@PostMapping("/addNew")
	public ResponseEntity<?> newCustomer(@RequestBody CustomerDto dto) {
		return new ResponseEntity<SuccessMessageDto>(new SuccessMessageDto(customerService.createCustomer(dto)),
				HttpStatus.CREATED);
	}

	@ApiOperation(value = "/getAllCustomers", notes = "This api is used to get all Customer", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getCustomers")
	public ResponseEntity<?> getCustomers() {
		return new ResponseEntity<List<CustomerResponseDto>>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@ApiOperation(value = "/UpdateCustomer", notes = "This api is used to update a Customer", httpMethod = "PUT")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@PutMapping("/getCustomer/{customerId}")
	public ResponseEntity<?> updateCust(@PathVariable int customerId, @RequestBody CustomerDto dto) {
		return new ResponseEntity<CustomerDto>(customerService.updateCustomer(customerId, dto), HttpStatus.CREATED);
	}

}
