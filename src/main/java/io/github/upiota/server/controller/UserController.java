package io.github.upiota.server.controller;

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
import io.github.upiota.server.sys.entity.User;
import io.github.upiota.server.sys.mapper.UserMapper;

@RestController
@RequestMapping("user")
@AuthResource(code = "user_manager", name = "用户管理", type = AuthResourceType.MOUDLE)
public class UserController extends BaseController {

	@Autowired
	private UserMapper userRepository;

	@GetMapping("list")
	// @PreAuthorize("hasAuthority('systemManager')")
	// @PreAuthorize("hasRole('systemManager')")
	@AuthResource(code = "user_list", name = "用户列表查询")
	public ResponseResult list() {
		// Long userId = getCurrentUserId();
		List<User> list = userRepository.selectAll();
		return RestResultGenerator.genResult("成功!").putData("list", list);
	}

}
