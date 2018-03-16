package io.github.upiota.server.sys.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import io.github.upiota.server.base.BaseEntity;

@Table(name = "up_authority")
public class Authority extends BaseEntity{
	
	@Column(name = "authority_code")
	private String authorityCode;
	
	public String getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}
	
	
}
