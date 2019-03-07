package com.cihan.swing.model.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/** @author Cihan SELECT last_value FROM seq_user */

@Entity
@Table(name = "usr")
public class User {
	private Integer id;
	private String username;
	private String password;
	private String uname;
	private String surname;
	private String email;
	private Rol rol;
	private Date UserInsertDate;
	private Date UserUpdateDate;
	private Date UserDeleteDate;
	private Integer durum;
	
	@Id
	@SequenceGenerator(name = "seq_user", allocationSize = 1, sequenceName = "seq_user")
	@GeneratedValue(generator = "seq_user", strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 100, name = "username", unique = true , nullable=false)
	@OrderBy("username ASC")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "uname")	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "userinsertdate")
	public Date getUserInsertDate() {
		return UserInsertDate;
	}

	public void setUserInsertDate(Date UserInsertDate) {
		this.UserInsertDate = UserInsertDate;
	}

	@Column(name = "userdeletedate")
	public Date getUserDeleteDate() {
		return UserDeleteDate;
	}

	public void setUserDeleteDate(Date UserDeleteDate) {
		this.UserDeleteDate = UserDeleteDate;
	}
	
	public Date getUserUpdateDate() {
		return UserUpdateDate;
	}
	
	@Column(name = "userupdatedate")
	public void setUserUpdateDate(Date userUpdateDate) {
		UserUpdateDate = userUpdateDate;
	}
	
	@Column(name = "durum")
	public Integer getDurum() {
		return durum;
	}

	public void setDurum(Integer durum) {
		this.durum = durum;
	}

	
	@Column(name = "rol_id")
    @Enumerated
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
