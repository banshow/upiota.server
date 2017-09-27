package io.github.upiota.server.mycoder.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coder")
public class CoderProperties {
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	private String javaTargetProject;
	private String resourcesTargetProject;
	
	private String basePackage;
	private String utilPackage;
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJavaTargetProject() {
		return javaTargetProject;
	}
	public void setJavaTargetProject(String javaTargetProject) {
		this.javaTargetProject = javaTargetProject;
	}
	public String getResourcesTargetProject() {
		return resourcesTargetProject;
	}
	public void setResourcesTargetProject(String resourcesTargetProject) {
		this.resourcesTargetProject = resourcesTargetProject;
	}
	public String getBasePackage() {
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	public String getUtilPackage() {
		return utilPackage;
	}
	public void setUtilPackage(String utilPackage) {
		this.utilPackage = utilPackage;
	}
	
	
	
}
