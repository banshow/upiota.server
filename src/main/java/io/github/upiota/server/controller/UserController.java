package io.github.upiota.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.upiota.server.base.BaseController;
import io.github.upiota.server.base.ResponseResult;
import io.github.upiota.server.base.RestResultGenerator;
import io.github.upiota.server.entity.User;
import io.github.upiota.server.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController extends BaseController{

	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("list")
	@PreAuthorize("hasAuthority('systemManager')")
	//@PreAuthorize("hasRole('systemManager')")
	public ResponseResult list(){
		//Long userId = getCurrentUserId();
		List<User> list = userRepository.selectAll();
		return RestResultGenerator.genResult("成功!").putData("list", list);
	}
	
}
