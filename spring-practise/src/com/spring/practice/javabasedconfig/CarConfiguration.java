package com.spring.practice.javabasedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(EngineConfiguration.class)
@Configuration
public class CarConfiguration {
	@Bean
	public Car carType() {
		return new Car(new Engine());
	}
}
