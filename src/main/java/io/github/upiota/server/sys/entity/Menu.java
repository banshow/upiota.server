package io.github.upiota.server.sys.entity;

import javax.persistence.*;
import java.lang.Long;


@Table(name = "up_menu")
public class Menu {

     //主键
     @Id
     @Column(name = "id")
     private Long id;
     
     //父id
     @Column(name = "parent_id")
     private Long parentId;
     
     //菜单标识
     @Column(name = "key")
     private String key;
     
     //菜单名称
     @Column(name = "name")
     private String name;
     
     //菜单图标
     @Column(name = "icon")
     private String icon;
     
     //菜单路径
     @Column(name = "path")
     private String path;
     
     //目标窗口
     @Column(name = "target")
     private String target;
     
     
     public Long getId() {
        return id;
     }

     public void setId(Long id) {
        this.id = id;
     }
     
     public Long getParentId() {
        return parentId;
     }

     public void setParentId(Long parentId) {
        this.parentId = parentId;
     }
     
     public String getKey() {
        return key;
     }

     public void setKey(String key) {
        this.key = key;
     }
     
     public String getName() {
        return name;
     }

     public void setName(String name) {
        this.name = name;
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
     
     
}