package io.github.upiota.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.github.upiota.framework.mybatis.data.MyMapperFactoryBean;
import io.github.upiota.server.mycoder.model.CoderProperties;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties({ CoderProperties.class })
@MapperScan(basePackages = { "io.github.upiota.server.mapper",
		"io.github.upiota.framework.sys.mapper"},factoryBean=MyMapperFactoryBean.class)
// @EnableMybatisRepositories(repositoryFactoryBeanClass=CustomMybatisRepositoryFactoryBean.class,repositoryBaseClass=CustomSimpleMybatisRepository.class)
public class Application{

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
