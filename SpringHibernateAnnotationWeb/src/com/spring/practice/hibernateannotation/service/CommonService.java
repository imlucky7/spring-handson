package com.spring.practice.hibernateannotation.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.practice.hibernateannotation.dao.DocumentDaoService;
import com.spring.practice.hibernateannotation.dao.GroupDaoService;
import com.spring.practice.hibernateannotation.dao.UserDaoService;
import com.spring.practice.hibernateannotation.model.Document;
import com.spring.practice.hibernateannotation.model.Group;
import com.spring.practice.hibernateannotation.model.User;

/**
 * @Transactional annotation should be used in service layer, not in DAO layer.
 * @Transactional annotation tells spring to create proxy which "wraps" 
 * annotated method with transaction using aspects.
 * 
 * @author pdutta
 *
 */
public class CommonService {

	Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private GroupDaoService groupDaoService;
	
	@Autowired
	private DocumentDaoService documentDaoService;
	
	public List<User> listUsersServiceTrans() {
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
	}
}
