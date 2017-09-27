package io.github.upiota.server.base;

import org.springframework.security.core.context.SecurityContextHolder;

import io.github.upiota.server.security.JwtUser;

public class BaseController {

	protected Long getCurrentUserId(){
		JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext().getAuthentication()
			    .getPrincipal();
		return userDetails.getId();
	}
}
