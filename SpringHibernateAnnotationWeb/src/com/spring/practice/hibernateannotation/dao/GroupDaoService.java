package com.spring.practice.hibernateannotation.dao;

import java.util.Iterator;
import java.util.Set;

import com.spring.practice.hibernateannotation.model.Document;
import com.spring.practice.hibernateannotation.model.Group;
import com.spring.practice.hibernateannotation.model.User;

public class GroupDaoService extends AbstractDaoService<Group> {
	
	public int createGroupTrans(Group group) throws Exception {
		try {
			int id = createRecord(group);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public Group getGroupTrans(Class<Group> type, String objectId) {
		Group group =  load(type, objectId);
		String grpId = group.getGroupId();
		System.out.println("grpid= " + grpId);
		
		Set<User> users = group.getUsers();
		Iterator<User> ite = users.iterator();
		User u = ite.next();
		String userID = u.getUserId();

		Set<Document> docs = group.getDocuments();
		Iterator<Document> docIte = docs.iterator();
		Document doc = docIte.next();
		String docID = doc.getDocumentId();

		System.out.println("Group Id: " + grpId + ", User Id: " + userID
				+ ", Document ID: " + docID);
		
		return group;
	}
}
