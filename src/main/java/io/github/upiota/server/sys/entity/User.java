package io.github.upiota.server.sys.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table(name = "up_user")
public class User{
	
	
	@Id
	private Long id;
	
	private String username;
	
	private String password;

	@Column(name = "create_at")
	private Date createAt;


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



	public Date getCreateAt() {
		return createAt;
	}



	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}



	public List<String> getAuthoritys() {
		return authoritys;
	}



	public void setAuthoritys(List<String> authoritys) {
		this.authoritys = authoritys;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	

}
