package com.cihan.swing.model.log;

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
import com.cihan.swing.model.user.User;


@Entity
@Table(name = "logproduct")
public class LogProduct {

	private Integer id;
	private String text;
	private Date logDate ;
    private User user;
    
	public LogProduct() {
		
	}
	
	@Id
	@SequenceGenerator(name = "seq_log", allocationSize = 1, sequenceName = "seq_log")
	@GeneratedValue(generator = "seq_log", strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 1000, name = "text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "logdate")
	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	@ManyToOne
	@JoinColumn(name = "loguser" ,referencedColumnName = "id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
