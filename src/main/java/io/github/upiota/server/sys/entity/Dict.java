package io.github.upiota.server.sys.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import io.github.upiota.server.base.BaseEntity;

@Table(name = "up_dict")
public class Dict extends BaseEntity{
	
	private String value;
	private String label;
	private String type;
	private Integer sort;
	@Column(name = "parent_id")
	private Long parentId;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
