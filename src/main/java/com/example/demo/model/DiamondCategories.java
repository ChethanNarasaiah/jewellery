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
@Table(name = "td_diamond_cate")
public class DiamondCategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diamond_cate_id")
	private int id;

	@Column(name = "diamond_cate_name")
	private String categoryName;

	@Column(name = "diamond_cate_crtd_dt")
	private Date categoryCreatedDate;

	@Column(name = "diamond_cate_updt_dt")
	private Date categoryUpdatedDate;

	@OneToMany(mappedBy = "diamondCate")
	private List<DiamondProducts> diamondProduct;

	public DiamondCategories() {
	}

	public DiamondCategories(int id, String categoryName, Date categoryCreatedDate, Date categoryUpdatedDate,
			List<DiamondProducts> diamondProduct) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryCreatedDate = categoryCreatedDate;
		this.categoryUpdatedDate = categoryUpdatedDate;
		this.diamondProduct = diamondProduct;
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

	public List<DiamondProducts> getDiamondProduct() {
		return diamondProduct;
	}

	public void setDiamondProduct(List<DiamondProducts> diamondProduct) {
		this.diamondProduct = diamondProduct;
	}

}
