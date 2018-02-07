package io.github.upiota.server;

import java.lang.reflect.Method;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import io.github.upiota.framework.mybatis.data.MyMapperFactoryBean;
import io.github.upiota.server.mycoder.model.CoderProperties;

@SpringBootApplication
@EnableConfigurationProperties({ CoderProperties.class })
@MapperScan(basePackages = { "io.github.upiota.server.mapper",
		"io.github.upiota.server.sys.mapper" }, factoryBean = MyMapperFactoryBean.class)
// @EnableMybatisRepositories(repositoryFactoryBeanClass=CustomMybatisRepositoryFactoryBean.class,repositoryBaseClass=CustomSimpleMybatisRepository.class)
public class Application implements SpringApplicationRunListener{

//	@Bean
//	public ReloadableResourceBundleMessageSource messageSource() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:i18n/messages_zh_CN");
//		return messageSource;
//	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		// app.setWebEnvironment(false);
		app.run(args);
	}

	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub
		Map<String, Object> cls = context.getBeansWithAnnotation(Controller.class);
		cls.values().forEach(b->{
			Class<?> c = b.getClass();
			System.out.println(c.getName());
			Method[] ms = c.getDeclaredMethods();
			
			for(Method m : ms) {
				System.out.println(m.getName());
				System.out.println(m.getDeclaredAnnotations());
			}
		});
	}

	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finished(ConfigurableApplicationContext context, Throwable exception) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void starting() {
		// TODO Auto-generated method stub
		
	}
}
