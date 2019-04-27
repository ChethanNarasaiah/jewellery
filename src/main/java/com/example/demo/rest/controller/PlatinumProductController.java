package com.example.demo.rest.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.dto.SuccessMessageDto;
import com.example.demo.model.PlatinumProducts;
import com.example.demo.service.PlatinumProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/platinumProduct")
@Api(value = "PlatinumProduct")
public class PlatinumProductController {

	@Autowired
	private PlatinumProductService productService;

	@Autowired
	private RestTemplate restTemplate;

	@ApiOperation(value = "/post", notes = "This api is used to add Products", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@PostMapping("/addPlatProduct")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto dto) {
		return new ResponseEntity<SuccessMessageDto>(new SuccessMessageDto(productService.createProduct(dto)),
				HttpStatus.CREATED);
	}

	@ApiOperation(value = "/get", notes = "This api is used to get all Products", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getPlatProducts")
	public ResponseEntity<?> getAllProducts() {
		return new ResponseEntity<List<ProductResponseDto>>(productService.getAllProducts(), HttpStatus.OK);
	}

	@ApiOperation(value = "/getDate", notes = "This api is used to get Product Created Date", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getProductDate/{prduct_id}")
	public ResponseEntity<?> getProductDate(@PathVariable int prduct_id) {
		return new ResponseEntity<Date>(productService.getDate(prduct_id), HttpStatus.OK);
	}

	@ApiOperation(value = "/UpdateProduct", notes = "This api is used to update a Product", httpMethod = "PUT")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@PutMapping("/getProductr/{productId}")
	public ResponseEntity<?> updateCust(@PathVariable int productId, @RequestBody ProductDto dto) {
		return new ResponseEntity<ProductDto>(productService.updateProduct(productId, dto), HttpStatus.CREATED);
	}

	@ApiOperation(value = "/get", notes = "This api is used to get all Products", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getProductsByPage")
	public ResponseEntity<?> getProductsByPage() {
		return new ResponseEntity<List<ProductResponseDto>>(productService.getProductsByPage(), HttpStatus.OK);
	}

	@ApiOperation(value = "/get", notes = "This api is used to get all Products", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@RequestMapping(value = "/builders")
	public String getBuilderList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("https://k0jelb2x49.execute-api.ap-south-1.amazonaws.com/myCondo/builders",
				HttpMethod.GET, entity, String.class).getBody();
	}

	@ApiOperation(value = "/get", notes = "This api is used to get all Products", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 401, message = "UnAuthorized") })
	@GetMapping("/getProductsByCatId/{Cat_Id}")
	public ResponseEntity<?> getProducts(@PathVariable Integer Cat_Id) {
		return new ResponseEntity<List<ProductResponseDto>>(productService.getProductsByCatId(Cat_Id), HttpStatus.OK);
	}

	/*@GetMapping ("/getPagination")
	public Page<PlatinumProducts> productsPageable(Pageable pageable){
		return productService.productPageable(pageable);
	}*/
}
