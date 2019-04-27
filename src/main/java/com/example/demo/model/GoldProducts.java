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
@Table(name = "tb_gold_product")
public class GoldProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gold_prdt_id")
	private long id;

	@Column(name = "gold_img_name")
	private String imageName;

	@Column(name = "gold_prdt_name")
	private String productName;

	@Column(name = "gold_prdt_price")
	private double price;

	@Column(name = "gold_prdt_wgt")
	private double weightsInGram;

	@Column(name = "gold_prdt_crtd_dt")
	private Date productCreatedDate;

	@Column(name = "gold_prdt_updt_dt")
	private Date productUpdatedDate;

	@Column(name = "gold_prdt_count")
	private int quantity;

	@Column(name = "prduct_url")
	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Gold_cate_id", nullable = false)
	private GoldCategories goldCate;

	public GoldProducts() {
	}

	public GoldProducts(long id, String imageName, String productName, double price, double weightsInGram,
			Date productCreatedDate, Date productUpdatedDate, int quantity, String url, GoldCategories goldCate) {
		super();
		this.id = id;
		this.imageName = imageName;
		this.productName = productName;
		this.price = price;
		this.weightsInGram = weightsInGram;
		this.productCreatedDate = productCreatedDate;
		this.productUpdatedDate = productUpdatedDate;
		this.quantity = quantity;
		this.url = url;
		this.goldCate = goldCate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public GoldCategories getGoldCate() {
		return goldCate;
	}

	public void setGoldCate(GoldCategories goldCate) {
		this.goldCate = goldCate;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "GoldProducts [id=" + id + ", imageName=" + imageName + ", productName=" + productName + ", price="
				+ price + ", weightsInGram=" + weightsInGram + ", productCreatedDate=" + productCreatedDate
				+ ", productUpdatedDate=" + productUpdatedDate + ", quantity=" + quantity + ", url=" + url + "]";
	}
	

}
