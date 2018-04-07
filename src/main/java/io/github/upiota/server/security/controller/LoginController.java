package io.github.upiota.server.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@Autowired
	public OAuth2RestTemplate restTemplate;
	

    @RequestMapping("/login")
    public ResponseEntity<?> login(String username,String password) {
    	ResourceOwnerPasswordResourceDetails resource = (ResourceOwnerPasswordResourceDetails) restTemplate.getResource();
        resource.setUsername(username);
        resource.setPassword(password);
        OAuth2AccessToken accessToken = restTemplate.getAccessToken();
    	return ResponseEntity.ok(accessToken);
    }
    
//    @RequestMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
//    	 //new SecurityContextLogoutHandler().logout(request, response, null);
//    	return ResponseEntity.ok("");
//    }

}