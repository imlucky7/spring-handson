package com.spring.practice.jpa.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practice.jpa.dao.DocumentDaoService;
import com.spring.practice.jpa.dao.GroupDaoService;
import com.spring.practice.jpa.dao.UserDaoService;
import com.spring.practice.jpa.entity.Document;
import com.spring.practice.jpa.entity.Group;
import com.spring.practice.jpa.entity.User;
import com.spring.practice.jpa.util.DatabaseException;


/**
 * 
 * @author pdutta
 *
 */

@Service
public class CommonService {

	Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private GroupDaoService groupDaoService;
	
	@Autowired
	private DocumentDaoService documentDaoService;
	
	public List<Document> listUsersServiceTrans() {
		return documentDaoService.getDocumentList();
	}
	
	public List<User> getUserByDocId(String docId) {
		return userDaoService.getUserByDocId(docId);
	}
	
	public Group getGroupInfo(String userId) {
		return groupDaoService.getGroupByUserid(userId);
	}
	
	public void updateGroup(Group group) throws Exception {
		groupDaoService.updateGroup(group);
	}
	
	public void deleteGroup(Group group) {
		groupDaoService.deleteGroup(group);
	}
	/*public List<User> listUsersServiceTrans() {
		return userDaoService.listUsersTrans();
	}
	
	public Group getGroupServiceTrans(String groupId) {
		return groupDaoService.getGroupTrans(Group.class, groupId);
	}
	
	public int createGroupServiceTrans(Group grp) throws Exception {
		try {
			return groupDaoService.createGroupTrans(grp);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public Document getDocumentServiceTrans(String DocId) {
		return documentDaoService.load(Document.class, DocId);
	}
	
	public User getUserServiceTrans(String userId) {
		return userDaoService.load(User.class, userId);
	}
	
	public void updateDocumentServiceTrans(Document document) throws Exception {
		try {
			documentDaoService.updateDocument(document);
		}
		catch (Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	public void updateUserServiceTrans(User user) throws Exception {
		try {
			userDaoService.updateUserTrans(user);
		}
		catch (Exception e) {
			log.error(e);
			throw e;
		}
	}*/
}
