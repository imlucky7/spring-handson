package com.spring.practice.hibernate.dao;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import com.spring.practice.hibernate.exception.BeanManagedTransaction;
import com.spring.practice.hibernate.exception.DatabaseException;
import com.spring.practice.hibernate.model.Document;
import com.spring.practice.hibernate.model.Group;
import com.spring.practice.hibernate.model.User;

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
	
	public void updateGroupTrans() throws Exception {
		final String query1 = "com.spring.practice.hibernate.model.Document.updateTitle";
		final String query2 = "com.spring.practice.hibernate.model.User.mobileNo";
		
		try {
//			getHibernateTemplate().findByNamedQuery(query2, new Object[] {"123123123", "1"});

			Session sess =  getSessionFactory().getCurrentSession();

			Query q1 = sess.getNamedQuery(query2);
			q1.setString(0, "666666");
			q1.setString(1, "1");
			q1.executeUpdate();
			
			Query q2 = sess.getNamedQuery(query1);
			q2.setString(0, "ccccc");
			q2.setString(1, "1");
			q2.executeUpdate();
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			/**
			 * roleback will not take place if DatabaseException is thrown
			 */
//			throw new DatabaseException(e); 
		}
	}
}
