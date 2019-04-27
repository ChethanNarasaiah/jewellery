package com.example.demo.dto;

public class SuccessMessageDto {

	private String response;

	public SuccessMessageDto(String response) {
		super();
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
