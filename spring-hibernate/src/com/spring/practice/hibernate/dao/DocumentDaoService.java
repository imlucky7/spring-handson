package com.spring.practice.hibernate.dao;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.spring.practice.hibernate.exception.BeanManagedTransaction;
import com.spring.practice.hibernate.model.Document;

public class DocumentDaoService extends AbstractDaoService<Document> {
	HibernateTransactionManager txManager;

	public HibernateTransactionManager getTxManager() {
		return txManager;
	}

	public void setTxManager(HibernateTransactionManager txManager) {
		this.txManager = txManager;
	}

	@BeanManagedTransaction
	public void updateDocument() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = null;
		final String query1 = "com.spring.practice.hibernate.model.Document.updateTitle";
		final String query2 = "com.spring.practice.hibernate.model.Document.updateCreationDate";
		try {
			status = txManager.getTransaction(def);
			Session sess = getSessionFactory().getCurrentSession();

			Query q2 = sess.getNamedQuery(query2);
			q2.setDate(0, new Date());
			q2.setString(1, "1");
			q2.executeUpdate();
			
			Query q1 = sess.getNamedQuery(query1);
			q1.setString(0, "title111");
			q1.setString(1, "1");
			q1.executeUpdate();
			
			txManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			txManager.rollback(status);
		}
	}
}
