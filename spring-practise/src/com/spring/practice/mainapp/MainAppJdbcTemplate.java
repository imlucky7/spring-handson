package com.spring.practice.mainapp;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.practice.jdbctemplate.Zstudent;
import com.spring.practice.jdbctemplate.ZstudentJDBCTemplate;

public class MainAppJdbcTemplate {

	public static void main(String[] args) {
		 ApplicationContext context = 
             new ClassPathXmlApplicationContext("jdbctemplate-beans.xml");
		 
		 ZstudentJDBCTemplate studentJDBCTemplate = 
		      (ZstudentJDBCTemplate)context.getBean("studentJDBCTemplate");
		 
		 studentJDBCTemplate.create(1, "pranab", 11);
		 studentJDBCTemplate.create(2, "pranab1", 12);
		 studentJDBCTemplate.create(3, "dutta", 11);
		 
		 /***************************************/
		 
		System.out.println("------Listing Multiple Records--------");
		List<Zstudent> students = studentJDBCTemplate.listStudents();
		for (Zstudent record : students) {
			System.out.print("ID : " + record.getId());
			System.out.print(", Name : " + record.getName());
			System.out.println(", Age : " + record.getAge());
		}
	}

}
