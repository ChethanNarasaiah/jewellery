package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_id")
	private int id;

	@Column(name = "total_cost")
	private double totalCost;

	@Column(name = "sales_crtd_dt")
	private Date createdDate;

	@Column(name = "sales_quantity")
	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id", nullable = false)
	private Customer customer;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tb_product_sales", joinColumns = @JoinColumn(name = "Sales_id", referencedColumnName = "Sales_id"), inverseJoinColumns = @JoinColumn(name = "prduct_id", referencedColumnName = "prduct_id"))
	private List<PlatinumProducts> product = new ArrayList<>();
	
	/*@ManyToMany(mappedBy = "sales")
	private List<Product> product;*/
	
	public Sales() {
		super();
	}

	public Sales(int id, double totalCost, Date createdDate, int quantity) {
		super();
		this.id = id;
		this.totalCost = totalCost;
		this.createdDate = createdDate;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<PlatinumProducts> getProduct() {
		return product;
	}

	public void setProduct(List<PlatinumProducts> product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", totalCost=" + totalCost + ", createdDate=" + createdDate + ", quantity="
				+ quantity + ", customer=" + customer + ", product=" + product + "]";
	}

}
