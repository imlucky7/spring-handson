package com.spring.practice.hibernateannotation.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.practice.hibernateannotation.dao.DocumentDaoService;
import com.spring.practice.hibernateannotation.dao.GroupDaoService;
import com.spring.practice.hibernateannotation.dao.UserDaoService;
import com.spring.practice.hibernateannotation.formbean.InsertDataBean;
import com.spring.practice.hibernateannotation.model.Document;
import com.spring.practice.hibernateannotation.model.Group;
import com.spring.practice.hibernateannotation.model.User;
import com.spring.practice.hibernateannotation.service.CommonService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/")
	public ModelAndView home() {
		List<User> listUsers = commonService.listUsersServiceTrans();
		ModelAndView model = new ModelAndView("home");
		model.addObject("userList", listUsers);
		return model;
	}
	
	@RequestMapping(value="/groupPage")
	public ModelAndView groupPage(@RequestParam("groupId") String groupId) {
		log.info("inside groupPage controller.. groupId is : " + groupId);
		Group grp = commonService.getGroupServiceTrans(groupId);
		ModelAndView model = new ModelAndView("group");
		model.addObject("group", grp);
		return model;
	}
	
	@RequestMapping(value="/insertDataPage")
	public ModelAndView insertDataPage() {
		ModelAndView model = new ModelAndView("insertDataPage", "insertData", new InsertDataBean());
		return model;
	}
	
	@RequestMapping(value="/editUser")
	public ModelAndView editUser(@RequestParam("userId") String userId) {
		User user = commonService.getUserServiceTrans(userId);
		ModelAndView model = new ModelAndView("user");
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public String updateUser(@ModelAttribute("SpringWeb")User user) {
		log.info("inside updateUser controller.. User address is : " + user.getAddress());
		try {
			commonService.updateUserServiceTrans(user);
		} catch (Exception e) {
			log.error(e);
		}
		return "redirect:";
	}
	
	@RequestMapping(value="/editDoc")
	public ModelAndView editDoc(@RequestParam("docId") String docId) {
		log.info("inside editDoc controller.. docId is : " + docId);
		Document document = commonService.getDocumentServiceTrans(docId);
		ModelAndView model = new ModelAndView("document");
		model.addObject("document", document);
		return model;
	}
	
	@RequestMapping(value="/updateDoc", method=RequestMethod.POST)
	public String updateDoc(@ModelAttribute("SpringWeb")Document document) {
		log.info("inside editDoc controller.. doc title is : " + document.getTitle());
		try {
			commonService.updateDocumentServiceTrans(document);
		} catch (Exception e) {
			log.error(e);
		}
		return "redirect:";
	}
	
	@RequestMapping(value="/insertData")
	public String insertData(@ModelAttribute("SpringWeb")InsertDataBean insertDataBean) {
		log.info("Inside insertData controller - grp id: "+insertDataBean.getGroupId());
		
		User user = new User();
		user.setUserId(insertDataBean.getUserId());
		user.setUserName("dummy user");
		user.setAddress("dummy address");
		user.setMobileNo("1111222223333");

		Document document = new Document();
		document.setDocumentId(insertDataBean.getDocId());
		document.setTitle("dummy");
		document.setCrateBy("dummy user");
		document.setCreateDate(new Date());

		Group grp = new Group();
		grp.setGroupId(insertDataBean.getGroupId());
		grp.setDescription("dummy group");
		grp.setGroupName("dummy-grp");

		Set<User> userSet = new HashSet<User>();
		userSet.add(user);
		grp.setUsers(userSet);

		Set<Document> documents = new HashSet<Document>();
		documents.add(document);
		grp.setDocuments(documents);
		
		user.setGroup(grp);
		try {
			commonService.createGroupServiceTrans(grp);
		} catch (Exception e) {
			log.error(e);
		}
		return "redirect:";
	}
}
