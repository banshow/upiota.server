package io.github.upiota.server.config;

import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import io.github.upiota.framework.annotation.ApiResource;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

@Component
public class MyOperationReader implements OperationBuilderPlugin{

	@Override
	public boolean supports(DocumentationType delimiter) {
		return true;
	}

	@Override
	public void apply(OperationContext context) {
		Optional<ApiResource> methodAnnotation = context.findAnnotation(ApiResource.class);
		if(methodAnnotation.isPresent()) {
			ApiResource operation = methodAnnotation.get();
			 String operationName = operation.name();
				context.operationBuilder()
			            .summary(operationName);
		}
	   
		
	}

}
