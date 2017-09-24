package com.spring.practice.declarativetxn;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class StudentJDBCTemplate implements StudentDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@BeanManagedTransaction
	public void create(Integer id, String name, Integer age, Integer marks, Integer year) 
		throws DataAccessException, DatabaseException {
		try {
			String SQL1 = "insert into ZSTUDENT (id, name, age) values (?, ?, ?)";
			jdbcTemplateObject.update(SQL1, id, name, age);

			// Get the latest student id to be used in Marks table
			String SQL2 = "select max(id) from ZSTUDENT";
			int sid = jdbcTemplateObject.queryForInt(SQL2);
			
			if(sid == 2)
				sid--;
			
			if(sid == 3)
				throw new DatabaseException("");
			
			String SQL3 = "insert into ZMARKS(sid, marks, year) "
					+ "values (?, ?, ?)";
			jdbcTemplateObject.update(SQL3, sid, marks, year);

			System.out.println("Created Name = " + name + ", Age = " + age);
		} catch (DataAccessException e) {
			System.out.println("Error in creating record, rolling back");
			throw e; // Rollback happens
			//throw new DatabaseException("" ); //Rollback doesn't happen
		} catch (DatabaseException e) {
			System.out.println("DatabaseException occurred in creating record, rollback doesn't happen..");
			throw e;
		}
	}

	public List<StudentMarks> listStudents() {
		String SQL = "select * from ZSTUDENT, ZMARKS where ZSTUDENT.id=ZMARKS.sid";
		List<StudentMarks> studentMarks = jdbcTemplateObject.query(SQL,
				new StudentMarksMapper());
		return studentMarks;
	}
}
