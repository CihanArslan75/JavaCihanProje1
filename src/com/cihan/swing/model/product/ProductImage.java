package com.cihan.swing.model.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "productimage")
public class ProductImage {
	         
	private Integer id;
	private ProductStock productStock;
	private byte[] productImage;
	
		
	@Id
	@SequenceGenerator(name = "seq_productimage", allocationSize = 1, sequenceName = "seq_productimage")
	@GeneratedValue(generator = "seq_productimage", strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@OneToOne
	@JoinColumn(name = "productstockid", referencedColumnName = "id")
	public ProductStock getProductStock() {
		return productStock;
	}

	public void setProductStock(ProductStock productStock) {
		this.productStock = productStock;
	}
	
	@Lob
	//@Column(name="productimage",  columnDefinition="mediumblob")
	@Column(name="productimage")
	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

}
	