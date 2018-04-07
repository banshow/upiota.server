package io.github.upiota.server.sys.controller;

import java.security.Principal;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	
	@RequestMapping({"/","/index"})
	public String index(Principal principal,OAuth2Authentication oAuth2Authentication) {
		return "1111111";
	}
	@RequestMapping({"/user"})
	public String user(Principal principal,OAuth2Authentication oAuth2Authentication) {
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
		System.out.println(principal);
		return "aaa";
	}
	@RequestMapping({"/aaa"})
	public String aaa(Principal principal) {
		System.out.println(principal);
		return "aaa";
	}
}
