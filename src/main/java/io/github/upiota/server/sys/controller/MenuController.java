package io.github.upiota.server.sys.controller;

import io.github.upiota.server.sys.entity.Menu;
import io.github.upiota.server.sys.entity.User;

import org.springframework.web.bind.annotation.GetMapping;

import io.github.upiota.framework.annotation.AuthResource;
import io.github.upiota.server.base.ResponseResult;
import io.github.upiota.server.base.RestResultGenerator;
import io.github.upiota.server.sys.service.MenuService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("menu")
public class MenuController{

	@Autowired
	private MenuService menuService;
	
	
	@GetMapping("tree4User")
	// @PreAuthorize("hasAuthority('systemManager')")
	// @PreAuthorize("hasRole('systemManager')")
	@AuthResource(code = "menu_tree4User", name = "用户菜单树")
	public ResponseResult list() {
		List<Menu> list = menuService.tree4User();
		return RestResultGenerator.genResult("成功!").putData("list", list);
	}
	
	
	
	
}