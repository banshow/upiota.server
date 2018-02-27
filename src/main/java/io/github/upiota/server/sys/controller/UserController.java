package io.github.upiota.server.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.upiota.framework.annotation.ApiResource;
import io.github.upiota.framework.annotation.Authority;
import io.github.upiota.server.base.BaseController;
import io.github.upiota.server.base.ResponseResult;
import io.github.upiota.server.base.RestResultGenerator;
import io.github.upiota.server.sys.entity.Menu;
import io.github.upiota.server.sys.entity.User;
import io.github.upiota.server.sys.mapper.UserMapper;
import io.github.upiota.server.sys.service.MenuService;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	private UserMapper userRepository;
	
	@Autowired
	private MenuService menuService;

	//@GetMapping("list")
	// @PreAuthorize("hasAuthority('systemManager')")
	// @PreAuthorize("hasRole('systemManager')")
	@ApiResource(path = "list",name = "用户列表查询",authority = Authority.login_user_read)
	public ResponseResult list() {
		// Long userId = getCurrentUserId();
		List<User> list = userRepository.selectAll();
		return RestResultGenerator.genResult("成功!").putData("list", list);
	}

	@ApiResource(
			name = "当前用户信息查询",
			path = "currentInfo",
			authority = Authority.login_user_read,
			method = RequestMethod.GET
			)
	//@ApiOperation(value = "aaaa")
	//@Secured("user:currentInfo")
	//@PreAuthorize("hasAuthority('user:currentInfo')")
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
