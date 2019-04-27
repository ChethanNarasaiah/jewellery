package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_Platinum_products")
public class PlatinumProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prduct_id")
	private int id;

	@Column(name = "prduct_name")
	private String productName;

	@Column(name = "prduct_price")
	private double price;

	@Column(name = "prduct_wgt")
	private double weightsInGram;

	@Column(name = "prduct_crtd_dt")
	private Date productCreatedDate;

	@Column(name = "prduct_updt_dt")
	private Date productUpdatedDate;

	@Column(name = "prduct_count")
	private int quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Category_id", nullable = false)
	private PlatinumCategory platinumCategory;

	@ManyToMany(mappedBy = "product")
	private List<Sales> sales;

	/*
	 * @ManyToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "tb_product_sales", joinColumns = @JoinColumn(name =
	 * "prduct_id", referencedColumnName = "prduct_id"), inverseJoinColumns
	 * = @JoinColumn(name = "Sales_id", referencedColumnName = "Sales_id"))
	 * private List<Sales> sales = new ArrayList<>();
	 */

	public PlatinumProducts() {
		super();
	}

	public PlatinumProducts(int id, String productName, double price, double weightsInGram, Date productCreatedDate,
			Date productUpdatedDate, int quantity, PlatinumCategory platinumCategory, List<Sales> sales) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.weightsInGram = weightsInGram;
		this.productCreatedDate = productCreatedDate;
		this.productUpdatedDate = productUpdatedDate;
		this.quantity = quantity;
		this.platinumCategory = platinumCategory;
		this.sales = sales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getWeightsInGram() {
		return weightsInGram;
	}

	public void setWeightsInGram(double weightsInGram) {
		this.weightsInGram = weightsInGram;
	}

	public Date getProductCreatedDate() {
		return productCreatedDate;
	}

	public void setProductCreatedDate(Date productCreatedDate) {
		this.productCreatedDate = productCreatedDate;
	}

	public Date getProductUpdatedDate() {
		return productUpdatedDate;
	}

	public void setProductUpdatedDate(Date productUpdatedDate) {
		this.productUpdatedDate = productUpdatedDate;
	}

	public List<Sales> getSales() {
		return sales;
	}

	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}

	public PlatinumCategory getPlatinumCategory() {
		return platinumCategory;
	}

	public void setPlatinumCategory(PlatinumCategory platinumCategory) {
		this.platinumCategory = platinumCategory;
	}

	@Override
	public String toString() {
		return "PlatinumProducts [id=" + id + ", productName=" + productName + ", price=" + price + ", weightsInGram="
				+ weightsInGram + ", productCreatedDate=" + productCreatedDate + ", productUpdatedDate="
				+ productUpdatedDate + ", quantity=" + quantity + "]";
	}

}
