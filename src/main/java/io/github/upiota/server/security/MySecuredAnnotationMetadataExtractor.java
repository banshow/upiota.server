package io.github.upiota.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.annotation.AnnotationMetadataExtractor;

import io.github.upiota.framework.annotation.ApiResource;

public class MySecuredAnnotationMetadataExtractor implements AnnotationMetadataExtractor<ApiResource>{

	@Override
	public Collection<? extends ConfigAttribute> extractAttributes(ApiResource securityAnnotation) {
		String attribute = securityAnnotation.authority().name();
		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		attributes.add(new SecurityConfig(attribute));
		return attributes;
	}

}
