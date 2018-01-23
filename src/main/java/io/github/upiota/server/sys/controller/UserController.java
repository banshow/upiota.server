package io.github.upiota.server.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.upiota.framework.annotation.AuthResource;
import io.github.upiota.framework.annotation.AuthResourceType;
import io.github.upiota.server.base.BaseController;
import io.github.upiota.server.base.ResponseResult;
import io.github.upiota.server.base.RestResultGenerator;
import io.github.upiota.server.sys.entity.Menu;
import io.github.upiota.server.sys.entity.User;
import io.github.upiota.server.sys.mapper.UserMapper;
import io.github.upiota.server.sys.service.MenuService;

@RestController
@RequestMapping("user")
@AuthResource(code = "user_manager", name = "用户管理", type = AuthResourceType.MOUDLE)
public class UserController extends BaseController {

	@Autowired
	private UserMapper userRepository;
	
	@Autowired
	private MenuService menuService;

	@GetMapping("list")
	// @PreAuthorize("hasAuthority('systemManager')")
	// @PreAuthorize("hasRole('systemManager')")
	@AuthResource(code = "user_list", name = "用户列表查询")
	public ResponseResult list() {
		// Long userId = getCurrentUserId();
		List<User> list = userRepository.selectAll();
		return RestResultGenerator.genResult("成功!").putData("list", list);
	}

	@GetMapping("currentInfo")
	@AuthResource(code = "current_info", name = "当前用户信息查询")
	public ResponseResult currentInfo() {
		List<Menu> list = menuService.tree4User();
		return RestResultGenerator.genResult("成功!")
				.putData("name", "admin")
				.putData("avatar", "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png")
				.putData("userid", "00000001")
				.putData("notifyCount", 12)
				.putData("menus", list);
	}
	
}
