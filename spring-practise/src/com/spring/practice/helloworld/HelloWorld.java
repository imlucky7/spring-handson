package com.spring.practice.helloworld;

public class HelloWorld {
	private String message;

	public void getMessage() {
		System.out.println("Bean provided message: " + message);
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
