package io.github.upiota.server.config;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import io.github.upiota.server.base.ResponseResult;
import io.github.upiota.server.base.RestResultGenerator;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter() {
			@Override
			protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {
				
				if(object instanceof InvalidGrantException) {
					InvalidGrantException ig = (InvalidGrantException) object;
					ResponseResult result = RestResultGenerator.genErrorResult(ig.getMessage(), ig.getOAuth2ErrorCode());
					super.writeInternal(result, result.getClass(), outputMessage);
					return;
				}
				
				if(object instanceof OAuth2Exception) {
					OAuth2Exception e = (OAuth2Exception) object;
					ResponseResult result = RestResultGenerator.genErrorResult(e.getMessage(), e.getOAuth2ErrorCode());
					super.writeInternal(result, result.getClass(), outputMessage);
					return;
				}
				
				if(object instanceof Exception) {
					Exception e = (Exception) object;
					ResponseResult result = RestResultGenerator.genErrorResult(e.getMessage(), "");
					super.writeInternal(result, result.getClass(), outputMessage);
					return;
				}
				
				if(!(object instanceof ResponseResult)) {
					ResponseResult result = RestResultGenerator.genResult("成功!").putData("result", object);
					super.writeInternal(result, result.getClass(), outputMessage);
					return;
				}
				
				super.writeInternal(object, type, outputMessage);
			}

			
		};
	}

	
	
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
	}


}
