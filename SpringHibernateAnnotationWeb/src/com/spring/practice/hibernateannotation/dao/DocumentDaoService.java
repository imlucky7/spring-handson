package com.spring.practice.hibernateannotation.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.spring.practice.hibernateannotation.exception.BeanManagedTransaction;
import com.spring.practice.hibernateannotation.model.Document;

public class DocumentDaoService extends AbstractDaoService<Document> {
	
	Log log = LogFactory.getLog(getClass());
	
	HibernateTransactionManager txManager;

	public HibernateTransactionManager getTxManager() {
		return txManager;
	}

	public void setTxManager(HibernateTransactionManager txManager) {
		this.txManager = txManager;
	}

	@BeanManagedTransaction
	public void updateDocument(Document document) throws Exception {
		
		log.info("Document Id : "+document.getDocumentId());
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = null;
		final String query1 = "com.spring.practice.hibernateannotation.model.Document.updateTitle";
		final String query2 = "com.spring.practice.hibernateannotation.model.Document.updateCreateBy";
		try {
			status = txManager.getTransaction(def);
			Session sess = getSessionFactory().getCurrentSession();

			Query q2 = sess.getNamedQuery(query2);
			q2.setString(0, document.getCrateBy());
			q2.setString(1, document.getDocumentId());
			q2.executeUpdate();
			
			Query q1 = sess.getNamedQuery(query1);
			q1.setString(0, document.getTitle());
			q1.setString(1, document.getDocumentId());
			q1.executeUpdate();
			
			txManager.commit(status);
		} catch (Exception e) {
			log.error(e);
			txManager.rollback(status);
			throw e;
		}
	}
}
