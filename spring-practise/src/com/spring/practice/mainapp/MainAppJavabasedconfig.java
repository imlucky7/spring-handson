package com.spring.practice.mainapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.practice.javabasedconfig.Car;
import com.spring.practice.javabasedconfig.CarConfiguration;
import com.spring.practice.javabasedconfig.Engine;

public class MainAppJavabasedconfig {
	public static void main(String[] args) {
		ApplicationContext ctx = 
			new AnnotationConfigApplicationContext(CarConfiguration.class);
		
		Car car = ctx.getBean(Car.class);
		car.getModel();
		
		/**
		 * CarConfiguration has imported EngineConfiguration class.
		 * That is why both beans are available here when only CarConfiguration 
		 * class is loaded to spring container.
		 */
		Engine engine = ctx.getBean(Engine.class);
		engine.getEngineSpecification();
	}
}
