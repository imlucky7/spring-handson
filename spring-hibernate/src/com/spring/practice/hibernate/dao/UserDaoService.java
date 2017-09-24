package com.spring.practice.hibernate.dao;

import java.util.List;

import com.spring.practice.hibernate.model.User;

public class UserDaoService extends AbstractDaoService<User> {
	@SuppressWarnings("unchecked")
	public List<User> getUserByDocTitleTrans(String title) throws Exception{
		final String query = "com.spring.practice.hibernate.model.User.getUserByDocTitle"; 
		List<User> userList = null;

		try {
			String[] paramNames = { "docTitle"};
			Object[] values = {title};

			/* execute and retrieve the project object */
			userList = (List<User>) getHibernateTemplate().findByNamedQueryAndNamedParam(query, paramNames, values);
		} catch (Exception e) {
			throw e;
		}
		return userList;
	}
}
