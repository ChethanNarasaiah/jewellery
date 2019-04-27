package com.example.demo.exceptions;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8715053732559162554L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
