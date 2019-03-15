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
import com.cihan.swing.model.base.BaseEntity;

/** @author Cihan */
@Entity
@Table(name = "product")
public class Product extends BaseEntity{

	private Integer id;
	private String productName;
	private Date productDate;
	private MarkaList productMark;
	private ProductTypeList productType;
	

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

	@Column(name = "producttypeid")
	public ProductTypeList getProductType() {
		return productType;
	}

	public void setProductType(ProductTypeList productType) {
		this.productType = productType;
	}

}
