package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_gold_cate")
public class GoldCategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gold_cate_id")
	private int id;

	@Column(name = "gold_cate_name")
	private String categoryName;

	@Column(name = "gold_cate_crtd_dt")
	private Date categoryCreatedDate;

	@Column(name = "gold_cate_updt_dt")
	private Date categoryUpdatedDate;

	@OneToMany(mappedBy = "goldCate")
	private List<GoldProducts> goldProduct;

	public GoldCategories() {
	}

	public GoldCategories(int id, String categoryName, Date categoryCreatedDate, Date categoryUpdatedDate,
			List<GoldProducts> goldProduct) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryCreatedDate = categoryCreatedDate;
		this.categoryUpdatedDate = categoryUpdatedDate;
		this.goldProduct = goldProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getCategoryCreatedDate() {
		return categoryCreatedDate;
	}

	public void setCategoryCreatedDate(Date categoryCreatedDate) {
		this.categoryCreatedDate = categoryCreatedDate;
	}

	public Date getCategoryUpdatedDate() {
		return categoryUpdatedDate;
	}

	public void setCategoryUpdatedDate(Date categoryUpdatedDate) {
		this.categoryUpdatedDate = categoryUpdatedDate;
	}

	public List<GoldProducts> getGoldProduct() {
		return goldProduct;
	}

	public void setGoldProduct(List<GoldProducts> goldProduct) {
		this.goldProduct = goldProduct;
	}

}
