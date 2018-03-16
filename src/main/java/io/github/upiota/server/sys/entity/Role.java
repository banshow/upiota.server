package io.github.upiota.server.sys.entity;


import javax.persistence.Column;
import javax.persistence.Table;

import io.github.upiota.server.base.BaseEntity;


@Table(name = "up_role")
public class Role  extends BaseEntity{
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_desc")
	private String roleDesc;

	
//	@ManyToMany
//	@JoinTable(name = "up_role_resource", 
//	joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, 
//	inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
//	private List<Resource> resources;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

//	public List<Resource> getResources() {
//		return resources;
//	}
//
//	public void setResources(List<Resource> resources) {
//		this.resources = resources;
//	}

}
