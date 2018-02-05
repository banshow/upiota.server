package io.github.upiota.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.annotation.AnnotationMetadataExtractor;

import io.github.upiota.framework.annotation.AuthResource;

public class MySecuredAnnotationMetadataExtractor implements AnnotationMetadataExtractor<AuthResource>{

	@Override
	public Collection<? extends ConfigAttribute> extractAttributes(AuthResource securityAnnotation) {
		String attribute = securityAnnotation.authority().name();
		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		attributes.add(new SecurityConfig(attribute));
		return attributes;
	}

}
