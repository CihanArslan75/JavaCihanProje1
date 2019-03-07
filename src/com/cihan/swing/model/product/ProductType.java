package com.cihan.swing.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** @author Cihan */
@Entity
@Table(name = "producttype")
public class ProductType {
	private Integer id;
	private String productTypeName;
	private Integer durum;

	@Id
	@SequenceGenerator(name = "seq_producttype", allocationSize = 1, sequenceName = "seq_producttype")
	@GeneratedValue(generator = "seq_producttype", strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 100, name = "producttypename")
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	@Column(name = "durum")
	public Integer getDurum() {
		return durum;
	}

	public void setDurum(Integer durum) {
		this.durum = durum;
	}

}
