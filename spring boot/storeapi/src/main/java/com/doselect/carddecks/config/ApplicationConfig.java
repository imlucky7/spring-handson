package com.doselect.carddecks.config;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.doselect.carddecks")
@PropertySource(ignoreResourceNotFound=true, value="classpath:shuffle.properties")
public class ApplicationConfig {
	
	@Named
	static class JerseyConfig extends ResourceConfig {
		public JerseyConfig() {
			this.packages("com.doselect.carddecks.rest");
		}
	}
	
}
