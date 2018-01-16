package io.github.upiota.server.sys.controller;

import io.github.upiota.server.sys.entity.Menu;
import org.springframework.web.bind.annotation.GetMapping;
import io.github.upiota.server.base.ResponseResult;
import io.github.upiota.server.base.RestResultGenerator;
import io.github.upiota.server.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("menu")
public class MenuController{

	@Autowired
	private MenuService menuService;
	
	
	
	
	
	
	
}