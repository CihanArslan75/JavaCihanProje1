package com.cihan.swing.model.product;

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

@Entity
@Table(name = "order")
public class Order extends BaseEntity{
	private Integer id;
	private Integer orderCount;
	private ProductStock productStock;
	
	@Id
	@SequenceGenerator(name = "seq_order", allocationSize = 1, sequenceName = "seq_order")
	@GeneratedValue(generator = "seq_order", strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "ordercount")
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	
	@ManyToOne
	@JoinColumn(name = "productid", referencedColumnName = "id")
	public ProductStock getProductStock() {
		return productStock;
	}
	public void setProductStock(ProductStock productStock) {
		this.productStock = productStock;
	}
	
}
