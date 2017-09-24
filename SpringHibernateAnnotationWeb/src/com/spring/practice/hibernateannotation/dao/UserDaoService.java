package com.spring.practice.hibernateannotation.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.spring.practice.hibernateannotation.exception.DatabaseException;
import com.spring.practice.hibernateannotation.model.User;

public class UserDaoService extends AbstractDaoService<User> {
	
	Log log = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByDocTitleTrans(String title) throws Exception{
		final String query = "com.spring.practice.hibernate.model.User.getUserByDocTitle"; 
		List<User> userList = null;

		try {
			String[] paramNames = { "docTitle"};
			Object[] values = {title};

			userList = (List<User>) getHibernateTemplate().findByNamedQueryAndNamedParam(query, paramNames, values);
		} catch (Exception e) {
			throw e;
		}
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> listUsersTrans() {
		log.info("Fetching all the Users from database...");
		try {
			List<User> userList = (List<User>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(User.class));
			return userList;
		} catch(Exception e) {
			log.error(e);
		}
		return null;
	}
	
	public void updateUserTrans(User user) throws Exception {
		final String query1 = "com.spring.practice.hibernateannotation.model.User.updateMobileNo";
		final String query2 = "com.spring.practice.hibernateannotation.model.User.updateAddress";
		
		try {
			Session sess =  getSessionFactory().getCurrentSession();

			Query q1 = sess.getNamedQuery(query1);
			q1.setString(0, user.getMobileNo());
			q1.setString(1, user.getUserId());
			q1.executeUpdate();
			
			Query q2 = sess.getNamedQuery(query2);
			q2.setString(0, user.getAddress());
			q2.setString(1, user.getUserId());
			q2.executeUpdate();
			
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw e;
			/**
			 * roleback will not take place if DatabaseException is thrown
			 */
//			throw new DatabaseException(e); 
		}
	}
}
