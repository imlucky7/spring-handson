package com.spring.practice.jpa.dao;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practice.jpa.entity.Document;
import com.spring.practice.jpa.repository.DocumentJpaRepo;

@Repository
public class DocumentDaoService {
	
	Log log = LogFactory.getLog(getClass());
	
	@Autowired
	DocumentJpaRepo documentJpaRepo;
	
	@Transactional
	public List<Document> getDocumentList() {
		Iterable<Document> docItr = documentJpaRepo.findAll();
		List<Document> docList = new ArrayList<Document>();
		for(Document doc : docItr) {
			docList.add(doc);
		}
		return docList;
	}
}
