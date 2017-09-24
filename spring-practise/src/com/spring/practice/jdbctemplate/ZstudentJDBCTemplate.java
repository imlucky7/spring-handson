package com.spring.practice.jdbctemplate;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ZstudentJDBCTemplate implements ZStudentDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	   
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer id, String name, Integer age) {
		String SQL = "insert into ZSTUDENT (ID, NAME, AGE) values (?, ?, ?)";
		jdbcTemplateObject.update(SQL, id, name, age);
		System.out.println("Created Record Name = " + name + " Age = " + age);
	}

	public Zstudent getStudent(Integer id) {
		String SQL = "select * from ZSTUDENT where id = ?";
		Zstudent student = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { id }, new ZstudentMapper());
		return student;
	}

	public List<Zstudent> listStudents() {
		String SQL = "select * from ZSTUDENT";
		List<Zstudent> students = jdbcTemplateObject.query(SQL, new ZstudentMapper());
		return students;
	}

	public void delete(Integer id) {
		String SQL = "delete from ZSTUDENT where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
	}

	public void update(Integer id, Integer age) {
		String SQL = "update ZSTUDENT set age = ? where id = ?";
		jdbcTemplateObject.update(SQL, age, id);
		System.out.println("Updated Record with ID = " + id);
	}

}
