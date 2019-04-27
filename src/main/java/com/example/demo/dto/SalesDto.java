package com.example.demo.dto;

import java.util.List;

public class SalesDto {

	private List<ProductDetailsDto> product;

	private int customerId;

	public List<ProductDetailsDto> getProduct() {
		return product;
	}

	public void setProduct(List<ProductDetailsDto> product) {
		this.product = product;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
