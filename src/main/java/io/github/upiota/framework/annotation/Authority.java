package io.github.upiota.framework.annotation;

public enum Authority {
	login_user_read("查询当前登录用户信息"),
	user_list_read("查询用户列表"),
	;
	
	private String name;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	private Authority(String name) {
		this.name = name;
	}
	
	
}
