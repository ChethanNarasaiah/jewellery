package com.example.demo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private int id;

	@Column(name = "cust_name")
	private String customerName;

	@Column(name = "phone_no")
	private String phoneNumber;

	@Column(name = "purchase_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfPurchase;

	@Column(name = "purchase_cost")
	@NotNull
	private double costOfPurchase;

	@Column(name = "address")
	@Size(max = 300)
	private String address;

	@OneToMany(mappedBy = "customer")
	private Set<Sales> sales;

	public Customer() {
		super();
	}

	public Customer(int id, String customerName, String phoneNumber, Date dateOfPurchase,
			@NotNull double costOfPurchase, @Size(max = 300) String address) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.dateOfPurchase = dateOfPurchase;
		this.costOfPurchase = costOfPurchase;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Set<Sales> getSales() {
		return sales;
	}

	public void setSales(Set<Sales> sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", phoneNumber=" + phoneNumber
				+ ", dateOfPurchase=" + dateOfPurchase + ", costOfPurchase=" + costOfPurchase + ", address=" + address
				+ "]";
	}

}
