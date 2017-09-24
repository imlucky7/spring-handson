package com.spring.practice.javabasedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineConfiguration {

	@Bean
	public Engine engineType() {
		return new Engine();
	}
}
