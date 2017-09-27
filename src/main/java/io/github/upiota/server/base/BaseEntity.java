package io.github.upiota.server.base;

import java.util.Date;

import javax.persistence.Column;


public class BaseEntity{


	@Column(name = "create_by")
	private Long createBy;
	@Column(name = "create_at")
	private Date createAt;
	@Column(name = "update_by")
	private Long updateBy;
	@Column(name = "update_at")
	private Date updateAt;
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	
	
}
