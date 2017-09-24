package com.spring.practice.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ZstudentMapper implements RowMapper<Zstudent> {

	public Zstudent mapRow(ResultSet rs, int rowNum) throws SQLException {
		Zstudent student = new Zstudent();
		student.setId(rs.getInt("id"));
		student.setName(rs.getString("name"));
		student.setAge(rs.getInt("age"));
		return student;
	}
}
