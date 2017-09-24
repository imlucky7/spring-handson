package com.spring.practice.autowire;

import org.springframework.beans.factory.annotation.Autowired;

public class Student {
	
	Book book;
	String name;
	int age;
	
	public String getName() {
		return name;
	}
	
	@Autowired
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	
	@Autowired(required=false)
	public void setAge(int age) {
		this.age = age;
	}
	
	@Autowired
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return this.book;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
}
