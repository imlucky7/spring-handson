package com.spring.practice.mainapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.practice.aspectjaop.Student;

public class MainAppAspectjaop {
	public static void main(String[] args) {
		 ApplicationContext context = 
             new ClassPathXmlApplicationContext("aspectjaop-beans.xml");

      Student student = (Student) context.getBean("student");

      student.getName();
      student.getAge();
      
      student.printThrowException();
	}
}
