package com.spring.practice.hibernate.mainapp;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.practice.hibernate.dao.DocumentDaoService;
import com.spring.practice.hibernate.dao.GroupDaoService;
import com.spring.practice.hibernate.dao.UserDaoService;
import com.spring.practice.hibernate.model.Document;
import com.spring.practice.hibernate.model.Group;
import com.spring.practice.hibernate.model.User;

public class MainApp {

	/**
	 * @param args
	 */
	static Logger logger = Logger.getLogger(MainApp.class);
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "hibernate-beans.xml", "dao-beans.xml" });
		
		logger.info("Logger is incorporated..");
		
		UserDaoService userDaoService = (UserDaoService) context
				.getBean("userDaoService");
		GroupDaoService groupDaoService = (GroupDaoService) context
				.getBean("groupDaoService");
		DocumentDaoService documentDaoService = (DocumentDaoService) context
				.getBean("documentDaoService");
		MainApp app = new MainApp();
		// app.testInsert(groupDaoService);

		// System.out.println("---------loading Group-----------");
		// app.testFetch(groupDaoService);

		// System.out.println("---------updating Group-----------");
		// app.testUpdate(groupDaoService);

		// System.out.println("----------loading Document via query-------");
		// app.testQuery(userDaoService);

		System.out
				.println("--------updating document via manaual declarative transaction-------");
		app.testUpdateDocument(documentDaoService);
	}

	private void testInsert(GroupDaoService groupDaoService) throws Exception {
		User user = new User();
		user.setUserId("1");
		user.setUserName("pranab");
		user.setAddress("kolkata");
		user.setMobileNo("9898");

		Document document = new Document();
		document.setDocumentId("1");
		document.setTitle("title");
		document.setCrateBy("pranab");
		document.setCreateDate(new Date());

		Group grp = new Group();
		grp.setGroupId("1");
		grp.setDescription("user gru");
		grp.setGroupName("user-grp");

		Set<User> userSet = new HashSet<User>();
		userSet.add(user);
		grp.setUsers(userSet);

		Set<Document> documents = new HashSet<Document>();
		documents.add(document);
		grp.setDocuments(documents);

		System.out.println(groupDaoService.createGroupTrans(grp));
	}

	private void testFetch(GroupDaoService groupDaoService) {
		groupDaoService.getGroupTrans(Group.class, "1");
	}

	private void testUpdate(GroupDaoService groupDaoService) throws Exception {
		groupDaoService.updateGroupTrans();

	}

	private void testQuery(UserDaoService uesrDaoService) throws Exception {
		String docTitle = "bbb";
		List<User> userList = uesrDaoService.getUserByDocTitleTrans(docTitle);

		for (User user : userList) {
			System.out.println(user.getUserId() + ", "
					+ user.getGroup().getGroupId());
		}
	}

	private void testUpdateDocument(DocumentDaoService documentDaoService)
			throws Exception {
		documentDaoService.updateDocument();
	}
}
