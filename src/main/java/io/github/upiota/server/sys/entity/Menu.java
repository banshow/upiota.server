package io.github.upiota.server.sys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.upiota.server.base.BaseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "up_menu")
public class Menu  extends BaseEntity{

     //父id
     @Column(name = "parent_id")
     private Long parentId;
     
     //菜单标识
     @Column(name = "menu_key")
     @JsonProperty("key")
     private String menuKey;
     
     //菜单名称
     @Column(name = "menu_name")
     @JsonProperty("name")
     private String menuName;
     
     //菜单图标
     @Column(name = "icon")
     private String icon;
     
     //菜单路径
     @Column(name = "path")
     private String path;
     
     //目标窗口
     @Column(name = "target")
     private String target;
     
     @Transient
     private List<Menu> children;
     
     public Long getParentId() {
        return parentId;
     }

     public void setParentId(Long parentId) {
        this.parentId = parentId;
     }
     
    
     
     public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getIcon() {
        return icon;
     }

     public void setIcon(String icon) {
        this.icon = icon;
     }
     
     public String getPath() {
        return path;
     }

     public void setPath(String path) {
        this.path = path;
     }
     
     public String getTarget() {
        return target;
     }

     public void setTarget(String target) {
        this.target = target;
     }

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
     
     
}