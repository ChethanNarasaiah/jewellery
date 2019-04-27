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
@Table(name = "tb_Plat_Category")
public class PlatinumCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plat_category_id")
	private int id;

	@Column(name = "plat_category_name")
	private String categoryName;

	@Column(name = "plat_category_crtd_dt")
	private Date categoryCreatedDate;

	@Column(name = "plat_category_updt_dt")
	private Date categoryUpdatedDate;

	@OneToMany(mappedBy = "platinumCategory")
	private List<PlatinumProducts> platinumProduct;

	public PlatinumCategory() {
		super();
	}

	public PlatinumCategory(int id, String categoryName, Date categoryCreatedDate, Date categoryUpdatedDate,
			List<PlatinumProducts> platinumProduct) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryCreatedDate = categoryCreatedDate;
		this.categoryUpdatedDate = categoryUpdatedDate;
		this.platinumProduct = platinumProduct;
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

	public List<PlatinumProducts> getPlatinumProduct() {
		return platinumProduct;
	}

	public void setPlatinumProduct(List<PlatinumProducts> platinumProduct) {
		this.platinumProduct = platinumProduct;
	}

	@Override
	public String toString() {
		return "PlatinumCategory [id=" + id + ", categoryName=" + categoryName + ", categoryCreatedDate="
				+ categoryCreatedDate + ", categoryUpdatedDate=" + categoryUpdatedDate + "]";
	}

}
