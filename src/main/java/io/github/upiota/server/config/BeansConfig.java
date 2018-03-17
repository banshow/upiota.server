package io.github.upiota.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sohu.idcenter.IdWorker;

@Configuration
public class BeansConfig {

	@Bean
	public IdWorker idWorker() {
		return new IdWorker();
	}
	
}
