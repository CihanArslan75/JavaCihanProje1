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
@Table(name = "productstock")
public class ProductStock extends BaseEntity {

	private Integer id;
	private Integer count;
	private Integer unitPrize;
	private SizeList sizeList;
	private ColorList productColor;
	private Integer saleRate;
	private Integer finalPrize;
	private Product product;

	@Id
	@SequenceGenerator(name = "seq_productstock", allocationSize = 1, sequenceName = "seq_productstock")
	@GeneratedValue(generator = "seq_productstock", strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "productid", referencedColumnName = "id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "productstockcount")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "productstockuprize")
	public Integer getUnitPrize() {
		return unitPrize;
	}

	public void setUnitPrize(Integer unitPrize) {
		this.unitPrize = unitPrize;
	}
	
	@Column(name = "productsizeno")
	public SizeList getSizeList() {
		return sizeList;
	}

	public void setSizeList(SizeList sizeList) {
		this.sizeList = sizeList;
	}
	
	@Column(name = "productcolor")
	public ColorList getProductColor() {
		return productColor;
	}

	public void setProductColor(ColorList productColor) {
		this.productColor = productColor;
	}
	
	@Column(name = "salerate")
	public Integer getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(Integer saleRate) {
		this.saleRate = saleRate;
	}
	@Column(name = "finalprize")
	public Integer getFinalPrize() {
		return finalPrize;
	}

	public void setFinalPrize(Integer finalPrize) {
		this.finalPrize = finalPrize;
	}
}