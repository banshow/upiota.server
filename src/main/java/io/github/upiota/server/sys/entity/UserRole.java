package io.github.upiota.server.sys.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import io.github.upiota.server.base.BaseEntity;

@Table(name = "up_user_role")
public class UserRole extends BaseEntity{

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "role_id")
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
