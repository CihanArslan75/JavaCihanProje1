package com.cihan.swing.model.product;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** @author Cihan */
@Entity
@Table(name = "product")
public class Product {

	private Integer id;
	private String productName;
	private Date productDate;
	private MarkaList productMark;
	private Integer deleteUser;
	private Date deleteDate;
	private Integer updateUser;
	private Date updateDate;
	private Integer insertUser;
	private Date insertDate;
	private ProductType productType;
	private Integer durum;
	

	@Id
	@SequenceGenerator(name = "seq_product", allocationSize = 1, sequenceName = "seq_product")
	@GeneratedValue(generator = "seq_product", strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 100, name = "productname")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "productdate")
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	@Column(name = "productmark")
	public MarkaList getProductMark() {
		return productMark;
	}

	public void setProductMark(MarkaList productMark) {
		this.productMark = productMark;
	}

	@ManyToOne
	@JoinColumn(name = "producttypeid", referencedColumnName = "id")
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Column(name = "deleteuser")
	public Integer getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(Integer deleteUser) {
		this.deleteUser = deleteUser;
	}

	@Column(name = "deletedate")
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Column(name = "updateuser")
	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "updatedate")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name = "insertuser")
	public Integer getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(Integer insertUser) {
		this.insertUser = insertUser;
	}
	@Column(name = "insertdate")
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	@Column(name = "durum")
	public Integer getDurum() {
		return durum;
	}

	public void setDurum(Integer durum) {
		this.durum = durum;
	}
}
