package io.github.upiota.server.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;


public class BaseEntity{

	@Id
	private Long id;
	
	@Column(name = "gmt_create")
	private Date gmtCreate;
	
	@Column(name = "gmt_modified")
	private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

}
