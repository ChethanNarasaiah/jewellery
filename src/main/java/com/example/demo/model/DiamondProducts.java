package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_diamond_product")
public class DiamondProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diamond_prdt_id")
	private long id;

	@Column(name = "diamond_prdt_name")
	private String productName;

	@Column(name = "diamond_prdt_price")
	private double price;

	@Column(name = "diamond_prdt_wgt")
	private double weightsInGram;

	@Column(name = "diamond_prdt_crtd_dt")
	private Date productCreatedDate;

	@Column(name = "diamond_prdt_updt_dt")
	private Date productUpdatedDate;

	@Column(name = "diamond_prdt_count")
	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diamond_cate_id", nullable = false)
	private DiamondCategories diamondCate;

	public DiamondProducts() {
	}

	public DiamondProducts(long id, String productName, double price, double weightsInGram, Date productCreatedDate,
			Date productUpdatedDate, int quantity, DiamondCategories diamondCate) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.weightsInGram = weightsInGram;
		this.productCreatedDate = productCreatedDate;
		this.productUpdatedDate = productUpdatedDate;
		this.quantity = quantity;
		this.diamondCate = diamondCate;
	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public DiamondCategories getDiamondCate() {
		return diamondCate;
	}

	public void setDiamondCate(DiamondCategories diamondCate) {
		this.diamondCate = diamondCate;
	}

}