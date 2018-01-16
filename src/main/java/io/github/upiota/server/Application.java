package io.github.upiota.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.upiota.framework.mybatis.data.MyMapperFactoryBean;
import io.github.upiota.server.mycoder.model.CoderProperties;

@SpringBootApplication
@EnableConfigurationProperties({ CoderProperties.class })
@MapperScan(basePackages = { "io.github.upiota.server.mapper",
		"io.github.upiota.server.sys.mapper" }, factoryBean = MyMapperFactoryBean.class)
// @EnableMybatisRepositories(repositoryFactoryBeanClass=CustomMybatisRepositoryFactoryBean.class,repositoryBaseClass=CustomSimpleMybatisRepository.class)
public class Application {

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
}
