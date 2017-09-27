package io.github.upiota.server.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "up_role")
public class Role {
	
	@Id
	private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
