package io.github.upiota.server.job;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.upiota.server.sys.entity.Authority;
import io.github.upiota.server.sys.mapper.AuthorityMapper;

@Component
public class AuthorityInitializer implements AppInitializer {
	
	
	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public void run() {
		List<String> list = authorityMapper.selectAll().stream().map(s->{
			return s.getAuthorityCode();
		}).collect(Collectors.toList());
		
		io.github.upiota.framework.annotation.Authority[] authoritys = io.github.upiota.framework.annotation.Authority.values();
		
		for(io.github.upiota.framework.annotation.Authority auth : authoritys) {
			String authorityCode = auth.name();
			
			if(list.contains(authorityCode)) {
				continue;
			}
			
			Authority authority = new Authority();
			
			authority.setAuthorityCode(authorityCode);
			
			authorityMapper.insert(authority);
			
		}
		
	}

}
