package io.github.upiota.server.sys.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	
	@RequestMapping({"/","/index"})
	public Principal index(Principal principal) {
		return principal;
	}
	@RequestMapping({"/user"})
	public String user(Principal principal) {
		System.out.println(principal);
		return "aaa";
	}
	@RequestMapping({"/aaa"})
	public String aaa(Principal principal) {
		System.out.println(principal);
		return "aaa";
	}
}
