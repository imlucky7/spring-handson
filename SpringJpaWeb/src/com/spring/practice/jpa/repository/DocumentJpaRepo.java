package com.spring.practice.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.practice.jpa.entity.Document;

public interface DocumentJpaRepo extends CrudRepository<Document, String>{
	
}
