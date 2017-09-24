package com.spring.practice.mainapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.practice.helloworld.HelloWorld;


public class MainAppHelloWorld {
	public static void main(String[] args) {
		ApplicationContext applicationContext =
			new ClassPathXmlApplicationContext("common-beans.xml");
		
		HelloWorld obj = (HelloWorld) applicationContext.getBean("helloWorld");
		
		obj.getMessage();
	}
}
