package com.spring.practice.declarativetxn;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;

public interface StudentDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Student and Marks
	 * tables.
	 */
	public void create(Integer id, String name, Integer age, Integer marks, Integer year) throws DataAccessException, DatabaseException;

	/**
	 * This is the method to be used to list down all the records from the
	 * Student and Marks tables.
	 */
	public List<StudentMarks> listStudents();
}
