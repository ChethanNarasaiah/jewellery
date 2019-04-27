package com.example.demo.dto;

import java.util.Date;

public class CustomerDto {

	private String customerName;
	private String phoneNumber;
	private Date dateOfPurchase;
	private double costOfPurchase;
	private String address;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public double getCostOfPurchase() {
		return costOfPurchase;
	}

	public void setCostOfPurchase(double costOfPurchase) {
		this.costOfPurchase = costOfPurchase;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
