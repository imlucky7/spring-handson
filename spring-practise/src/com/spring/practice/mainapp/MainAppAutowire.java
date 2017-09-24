package com.spring.practice.mainapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.practice.autowire.Student;

public class MainAppAutowire {

	public static void main(String[] args) {
		ApplicationContext applicationContext =
			new ClassPathXmlApplicationContext("autowire-beans.xml");
		
		Student obj = (Student) applicationContext.getBean("studentBean");
		
		System.out.println(obj.getBook().getName());
	}

}
