package io.github.upiota.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.github.upiota.server.mycoder.model.CoderProperties;




@SpringBootApplication
@EnableConfigurationProperties({CoderProperties.class})  
@MapperScan(basePackages={"io.github.upiota.server.repository","io.github.upiota.server.sys.repository"})
//@EnableMybatisRepositories(repositoryFactoryBeanClass=CustomMybatisRepositoryFactoryBean.class,repositoryBaseClass=CustomSimpleMybatisRepository.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		//app.setWebEnvironment(false);
		app.run(args);
	}
}
