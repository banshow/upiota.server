package io.github.upiota.server.config;

import java.util.List;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import io.github.upiota.server.security.MyAccessDecisionVoter;
import io.github.upiota.server.security.MySecuredAnnotationMetadataExtractor;

@EnableGlobalMethodSecurity
public class MyGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

	@Override
	protected AccessDecisionManager accessDecisionManager() {
		AffirmativeBased accessDecisionManager = (AffirmativeBased) super.accessDecisionManager();
		List<AccessDecisionVoter<? extends Object>> decisionVoters = accessDecisionManager.getDecisionVoters();
		MyAccessDecisionVoter myAccessDecisionVoter = new MyAccessDecisionVoter();
		decisionVoters.add(myAccessDecisionVoter);
		return accessDecisionManager;
	}

	@Override
	protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
		return new SecuredAnnotationSecurityMetadataSource(new MySecuredAnnotationMetadataExtractor());
	}
	
	

}
