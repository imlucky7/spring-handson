package com.spring.practice.mainapp;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.practice.programmatictxn.StudentJDBCTemplate;
import com.spring.practice.programmatictxn.StudentMarks;

public class MainAppProgrammaticTxn {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"programmatictxn-beans.xml");

		StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context
				.getBean("studentJDBCTemplate");

		System.out.println("------Records creation--------");

		studentJDBCTemplate.create(1, "Zara", 11, 99, 2010);
		studentJDBCTemplate.create(2, "Nuha", 20, 97, 2010);

		System.out.println("------Exception occurs--------");

		try {
			studentJDBCTemplate.create(3, "Pranab", 20, 97, 2010);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("------Listing all the records--------");
		List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
		for (StudentMarks record : studentMarks) {
			System.out.print("ID : " + record.getId());
			System.out.print(", Name : " + record.getName());
			System.out.print(", Marks : " + record.getMarks());
			System.out.print(", Year : " + record.getYear());
			System.out.println(", Age : " + record.getAge());
		}
	}

}
