package com.spring.practice.jpa.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.practice.jpa.entity.Document;
import com.spring.practice.jpa.entity.Group;
import com.spring.practice.jpa.entity.User;
import com.spring.practice.jpa.service.CommonService;

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
		List<Document> listDocs = commonService.listUsersServiceTrans();
		ModelAndView model = new ModelAndView("home");
		model.addObject("listDocs", listDocs);
		return model;
	}
	
	@RequestMapping(value="/userList")
	public ModelAndView userList(@RequestParam("docId") String docId) {
		log.info("inside userList controller.. docId is : " + docId);
		List<User> userList = commonService.getUserByDocId(docId);
		ModelAndView model = new ModelAndView("user");
		model.addObject("userList", userList);
		return model;
	}
	
	@RequestMapping(value="/groupInfo")
	public ModelAndView groupInfo(@RequestParam("userId") String userId) {
		Group grp = commonService.getGroupInfo(userId);
		ModelAndView model = new ModelAndView("group");
		model.addObject("group", grp);
		return model;
	}
	
	@RequestMapping(value="/editGroup", method=RequestMethod.POST)
	public String editGroup(@ModelAttribute("SpringWeb")Group group) {
		log.info("inside editGroup controller.. Group name is : " + group.getGroupName());
		try {
			commonService.updateGroup(group);
		} catch (Exception e) {
			log.error(e);
		}
		return "redirect:";
	}
	
	@RequestMapping(value="/deleteGroup", method=RequestMethod.POST)
	public String deleteGroup(@ModelAttribute("SpringWeb")Group group) {
		log.info("inside deleteGroup controller.. Group id is : " + group.getGroupId());
		try {
			commonService.deleteGroup(group);
		} catch (Exception e) {
			log.error(e);
		}
		return "redirect:";
	}
	
	/* @RequestMapping(value="/insertDataPage")
	public ModelAndView insertDataPage() {
		ModelAndView model = new ModelAndView("insertDataPage", "insertData", new InsertDataBean());
		return model;
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
	}*/
}
