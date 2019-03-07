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
@Table(name = "productstock")
public class ProductStock {

	private Integer id;
	private Integer count;
	private Integer unitPrize;
	private SizeList sizeList;
	private Integer deleteUser;
	private Date deleteDate;
	private Integer updateUser;
	private Date updateDate;
	private Product product;
	private Integer durum;

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
	
	@Column(name = "durum")
	public Integer getDurum() {
		return durum;
	}

	public void setDurum(Integer durum) {
		this.durum = durum;
	}

}