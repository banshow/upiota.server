package io.github.upiota.server.mycoder.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Config {

	private String modelName;
	private String tableName;
	
	private String modelPackage;	
	private String mapperPackage;
	private String sqlPackage;
	private String utilPackage;
	private boolean isGenService;
	private String servicePackage;
	private boolean isGenController;
	private String controllerPackage;
	private boolean insertApi;
	private boolean deleteApi;
	private boolean updateApi;
	private boolean listApi;
	private boolean pageApi;
	private boolean detailApi;
	
	
	private Set<String> mapperImportPackages = new LinkedHashSet<String>();
	private Set<String> serviceImportPackages = new LinkedHashSet<String>();
	private Set<String> controllerImportPackages = new LinkedHashSet<String>();
	
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getModelPackage() {
		return modelPackage;
	}
	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}
	public String getMapperPackage() {
		return mapperPackage;
	}
	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}
	public String getSqlPackage() {
		return sqlPackage;
	}
	public void setSqlPackage(String sqlPackage) {
		this.sqlPackage = sqlPackage;
	}

	public String getUtilPackage() {
		return utilPackage;
	}
	public void setUtilPackage(String utilPackage) {
		this.utilPackage = utilPackage;
	}
	public boolean isGenService() {
		return isGenService;
	}
	public void setGenService(boolean isGenService) {
		this.isGenService = isGenService;
	}
	public String getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
	public boolean isGenController() {
		return isGenController;
	}
	public void setGenController(boolean isGenController) {
		this.isGenController = isGenController;
	}
	public boolean isInsertApi() {
		return insertApi;
	}
	public void setInsertApi(boolean insertApi) {
		serviceImportPackages.add("io.github.upiota.server.base.util.UID");
		controllerImportPackages.add(modelPackage+"."+modelName);
		controllerImportPackages.add("org.springframework.web.bind.annotation.PostMapping");
		controllerImportPackages.add("io.github.upiota.server.base.ResponseResult");
		controllerImportPackages.add("io.github.upiota.server.base.RestResultGenerator");
		this.insertApi = insertApi;
	}
	public boolean isDeleteApi() {
		return deleteApi;
	}
	public void setDeleteApi(boolean deleteApi) {
		controllerImportPackages.add(modelPackage+"."+modelName);
		controllerImportPackages.add("org.springframework.web.bind.annotation.PostMapping");
		controllerImportPackages.add("io.github.upiota.server.base.ResponseResult");
		controllerImportPackages.add("io.github.upiota.server.base.RestResultGenerator");
		this.deleteApi = deleteApi;
	}
	public boolean isUpdateApi() {
		return updateApi;
	}
	public void setUpdateApi(boolean updateApi) {
		controllerImportPackages.add(modelPackage+"."+modelName);
		controllerImportPackages.add("org.springframework.web.bind.annotation.PostMapping");
		controllerImportPackages.add("io.github.upiota.server.base.ResponseResult");
		controllerImportPackages.add("io.github.upiota.server.base.RestResultGenerator");
		this.updateApi = updateApi;
	}
	public boolean isListApi() {
		return listApi;
	}
	public void setListApi(boolean listApi) {
		serviceImportPackages.add("java.util.List");
		controllerImportPackages.add("java.util.List");
		controllerImportPackages.add(modelPackage+"."+modelName);
		controllerImportPackages.add("org.springframework.web.bind.annotation.GetMapping");
		controllerImportPackages.add("io.github.upiota.server.base.ResponseResult");
		controllerImportPackages.add("io.github.upiota.server.base.RestResultGenerator");
		this.listApi = listApi;
	}
	public boolean isPageApi() {
		return pageApi;
	}
	public void setPageApi(boolean pageApi) {
		serviceImportPackages.add(utilPackage+".PageUtil");
		serviceImportPackages.add("io.github.upiota.server.base.PageInfo");
		serviceImportPackages.add("io.github.upiota.server.base.PageResult");
		controllerImportPackages.add("io.github.upiota.server.base.PageInfo");
		controllerImportPackages.add("io.github.upiota.server.base.PageResult");
		controllerImportPackages.add(modelPackage+"."+modelName);
		controllerImportPackages.add("org.springframework.web.bind.annotation.GetMapping");
		controllerImportPackages.add("io.github.upiota.server.base.ResponseResult");
		controllerImportPackages.add("io.github.upiota.server.base.RestResultGenerator");
		this.pageApi = pageApi;
	}
	public boolean isDetailApi() {
		controllerImportPackages.add(modelPackage+"."+modelName);
		controllerImportPackages.add("org.springframework.web.bind.annotation.GetMapping");
		controllerImportPackages.add("io.github.upiota.server.base.ResponseResult");
		controllerImportPackages.add("io.github.upiota.server.base.RestResultGenerator");
		return detailApi;
	}
	public void setDetailApi(boolean detailApi) {
		this.detailApi = detailApi;
	}
	public String getControllerPackage() {
		return controllerPackage;
	}
	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}
	public Set<String> getMapperImportPackages() {
		mapperImportPackages.add("org.apache.ibatis.annotations.Mapper");
		mapperImportPackages.add(modelPackage+"."+modelName);
		mapperImportPackages.add(utilPackage+".MyMapper");
		return mapperImportPackages;
	}
	public void setMapperImportPackages(Set<String> mapperImportPackages) {
		this.mapperImportPackages = mapperImportPackages;
	}
	public Set<String> getServiceImportPackages() {
		serviceImportPackages.add("org.springframework.beans.factory.annotation.Autowired");
		serviceImportPackages.add("org.springframework.stereotype.Service");
		serviceImportPackages.add(modelPackage+"."+modelName);
		serviceImportPackages.add(mapperPackage+"."+modelName+"Mapper");
		return serviceImportPackages;
	}
	public void setServiceImportPackages(Set<String> serviceImportPackages) {
		this.serviceImportPackages = serviceImportPackages;
	}
	public Set<String> getControllerImportPackages() {
		controllerImportPackages.add(servicePackage+"."+modelName+"Service");
		controllerImportPackages.add("org.springframework.beans.factory.annotation.Autowired");
		controllerImportPackages.add("org.springframework.web.bind.annotation.RequestMapping");
		controllerImportPackages.add("org.springframework.web.bind.annotation.RestController");
		return controllerImportPackages;
	}
	public void setControllerImportPackages(Set<String> controllerImportPackages) {
		this.controllerImportPackages = controllerImportPackages;
	}
	
}
