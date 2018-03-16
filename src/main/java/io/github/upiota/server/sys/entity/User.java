package io.github.upiota.server.sys.entity;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import io.github.upiota.server.base.BaseEntity;


@Table(name = "up_user")
public class User extends BaseEntity{
	
	
	private String username;
	
	private String password;

//	@ManyToMany
//	@JoinTable(name = "up_user_role", 
//	joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, 
//	inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	
	@Transient
	private List<String> authoritys;

	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<String> getAuthoritys() {
		return authoritys;
	}



	public void setAuthoritys(List<String> authoritys) {
		this.authoritys = authoritys;
	}

}
