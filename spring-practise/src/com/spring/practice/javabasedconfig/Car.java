package com.spring.practice.javabasedconfig;

public class Car {
	Engine engine;
	public Car(Engine engine) {
		this.engine = engine;
	}
	
	public void getModel() {
		engine.getEngineSpecification();
	}
}
