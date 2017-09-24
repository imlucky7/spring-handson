package com.spring.practice.mainapp;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.spring.practice.declarativetxn.DatabaseException;
import com.spring.practice.declarativetxn.StudentDAO;
import com.spring.practice.declarativetxn.StudentJDBCTemplate;
import com.spring.practice.declarativetxn.StudentMarks;

public class MainAppDeclarativeTxn {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"declarativetxn-beans.xml");

		StudentDAO studentJDBCTemplate = (StudentDAO) context
				.getBean("studentJDBCTemplate");

		System.out.println("------Records creation--------");

		try {
			studentJDBCTemplate.create(1, "Zara", 11, 99, 2010);
			System.out.println("------Exception occurs, rollback happen--------");
			studentJDBCTemplate.create(2, "Nuha", 20, 97, 2010);
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("------Exception occurs, rollback doesn't happen--------");
			studentJDBCTemplate.create(3, "Pranab", 20, 97, 2010);
		}
		catch (DatabaseException e) {
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
