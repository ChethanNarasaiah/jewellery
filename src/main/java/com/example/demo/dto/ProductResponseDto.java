package com.example.demo.dto;

public class ProductResponseDto {

	private long id;
	private String productName;
	private double price;
	private double weightsInGram;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeightsInGram() {
		return weightsInGram;
	}

	public void setWeightsInGram(double weightsInGram) {
		this.weightsInGram = weightsInGram;
	}

}
