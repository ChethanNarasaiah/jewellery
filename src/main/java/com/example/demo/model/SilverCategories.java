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
@Table(name = "tb_silver_cate")
public class SilverCategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "silver_cate_id")
	private int id;

	@Column(name = "silver_cate_name")
	private String categoryName;

	@Column(name = "silver_cate_crtd_dt")
	private Date categoryCreatedDate;

	@Column(name = "silver_cate_updt_dt")
	private Date categoryUpdatedDate;

	@OneToMany(mappedBy = "silverCate")
	private List<SilverProducts> silverProduct;

	public SilverCategories() {
	}

	public SilverCategories(int id, String categoryName, Date categoryCreatedDate, Date categoryUpdatedDate,
			List<SilverProducts> silverProduct) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryCreatedDate = categoryCreatedDate;
		this.categoryUpdatedDate = categoryUpdatedDate;
		this.silverProduct = silverProduct;
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

	public List<SilverProducts> getSilverProduct() {
		return silverProduct;
	}

	public void setSilverProduct(List<SilverProducts> silverProduct) {
		this.silverProduct = silverProduct;
	}

}
