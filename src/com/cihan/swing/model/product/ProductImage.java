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

import org.hibernate.engine.jdbc.BinaryStream;

@Entity
@Table(name = "productimage")
public class ProductImage {
	private Integer id;
	private ProductStock productStock;
	private byte[] productImage;
	
		
	@Id
	@SequenceGenerator(name = "seq_product", allocationSize = 1, sequenceName = "seq_product")
	@GeneratedValue(generator = "seq_product", strategy = GenerationType.SEQUENCE)
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
	@Column(name="productimage",  columnDefinition="mediumblob")
	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

}
	