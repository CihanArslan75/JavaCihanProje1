package com.cihan.swing.model.base;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	private Integer insertUser;
	private Date insertDate;
	private Integer updateUser;
	private Date updateDate;
	private Integer deleteUser;
	private Date deleteDate;
	private Integer state;
	
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

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
