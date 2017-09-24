package com.spring.practice.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.practice.jpa.entity.User;

public interface UserJpaRepo extends CrudRepository<User, String> {

	public final static String FIND_BY_DOC_ID = "SELECT u " +
			"FROM User u INNER JOIN u.group g LEFT OUTER JOIN g.documents doc " +
			"where doc.documentId = :docId";
	
	@Query(FIND_BY_DOC_ID)
	public List<User> findByDocId(@Param("docId") String documentId);
}
