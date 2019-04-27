package com.example.demo.advice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.dto.ErrorMessageDto;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.InvalidQuantityException;
import com.example.demo.exceptions.ProductCreationException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.exceptions.SalesException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException e) {
		return new ResponseEntity<ErrorMessageDto>(new ErrorMessageDto(e.getMessage()), HttpStatus.OK);
	}

	@ExceptionHandler(SalesException.class)
	public ResponseEntity<?> handleSalesException(SalesException e) {
		return new ResponseEntity<ErrorMessageDto>(new ErrorMessageDto(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(InvalidQuantityException.class)
	public ResponseEntity<?> handleInvaildQuantityException(InvalidQuantityException e) {
		return new ResponseEntity<ErrorMessageDto>(new ErrorMessageDto(e.getMessage()), HttpStatus.OK);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException e) {
		return new ResponseEntity<ErrorMessageDto>(new ErrorMessageDto(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(ProductCreationException.class)
	public ResponseEntity<?> handleCreationException(ProductCreationException e) {
		return new ResponseEntity<ErrorMessageDto>(new ErrorMessageDto(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
	}

}
