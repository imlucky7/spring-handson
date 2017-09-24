package com.findonnet.messaging;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.findonnet.persistence.MessageSequenceDAO;

/**
 * This class handles events and will simulate usecase1 and usecase2.. 
 * @author Murali Kosaraju
 */
public class EventHandler {
	
	private static final Log log = LogFactory.getLog(EventHandler.class);
	private JmsTemplate jmsTemplate;
	private ApplicationContext springContext = null;
	
	
	public void setSpringContext(ApplicationContext springContext) {
		this.springContext = springContext;
	}


	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}


	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	

	/**
	 * handleEvent - updates the database(s) first and then sends
	 * the message to the JMS Destination under a global transaction.
	 * Also handles usecase1 for updating two databases.
	 * @param fail - if true, generates a runtime exception to simulate
	 *                    an application exception for rollback purpsoses.
	 * @throws Exception
	 */
	public void handleEvent(boolean fail) throws Exception {

		MessageSequenceDAO dao = (MessageSequenceDAO) springContext	.getBean("sequenceDAO");
		int value = 13;
		String app = "spring";
		String appKey = "execution";
		int upCnt = dao.updateSequence(value, app, appKey);
		log.debug(" sql updCnt->" + upCnt);

		if (springContext.containsBean("sequenceDAO2")) {
			// this is for JBossTS
			MessageSequenceDAO dao2 = (MessageSequenceDAO) springContext.getBean("sequenceDAO2");
			appKey = "allocation";
			upCnt = dao2.updateSequence(value, app, appKey);
			log.debug(" sql updCnt2->" + upCnt);
		}

		if (springContext.containsBean("appSenderTemplate")) {
			this.setJmsTemplate((JmsTemplate) springContext.getBean("appSenderTemplate"));
			this.getJmsTemplate().convertAndSend("Testing 123456");
			log.debug("Sent message succesfully");
		}

		if (fail) {
			throw new RuntimeException("Simulating Rollback by throwing Exception !!");
		}
		
		
	}
	

}
