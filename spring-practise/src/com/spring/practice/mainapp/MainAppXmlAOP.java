package com.spring.practice.mainapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.practice.xmlaop.Student;

public class MainAppXmlAOP {
	public static void main(String[] args) {
		 ApplicationContext context = 
             new ClassPathXmlApplicationContext("xmlaop-beans.xml");

      Student student = (Student) context.getBean("student");

      student.getName();
      student.getAge();
      
      student.printThrowException();
	}
}
