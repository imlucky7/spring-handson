package com.findonnet.messaging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.findonnet.persistence.MessageSequenceDAO;

/**
 * This class handles usecase3- consumes a message from a queue and
 * updates the database in a global transaction.
 * 
 * This is our transactional MDP (message driven POJO).
 * 
 * @author Murali Kosaraju
 *
 */
public class MessageHandler {
	
	private static final Log log = LogFactory.getLog(MessageHandler.class);

	private static int value = 22;

	private boolean fail = false;

	public void setFail(boolean fail) {
		this.fail = fail;
	}

	public void handleOrder(String msg) {

		log.debug("Receieved message->: " + msg);

		MessageSequenceDAO dao = (MessageSequenceDAO) MainApp.springCtx.getBean("sequenceDAO");
		String app = "spring";
		String appKey = "allocation";
		int upCnt = dao.updateSequence(value++, app, appKey);
		log.debug("Update SUCCESS!! with Val " + value + " updateCnt->"+ upCnt);
		if (fail)
			throw new RuntimeException("TESTING");

	}

}
