package io.github.upiota.server.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import io.github.upiota.server.util.IdUtils;


public class BaseEntity{

	@Id
	private Long id;
	
	@Column(name = "create_at")
	private Date createAt;
	
	@Column(name = "modified_at")
	private Date modifiedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public void assignDefault() {
		this.setId(IdUtils.genId());
		Date now = new Date();
		this.setCreateAt(now);
		this.setModifiedAt(now);
	}
}
