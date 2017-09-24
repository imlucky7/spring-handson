package com.spring.practice.hibernate.dao;

import java.io.Serializable;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.spring.practice.hibernate.exception.DatabaseException;


public class AbstractDaoService<T> extends HibernateDaoSupport {
	
	public AbstractDaoService() {
		super();
	}
	
	/**
	 * Generic create method.
	 */
	
	public int createRecord(final T object) throws Exception {
		HibernateTemplate template = null;
		try {
			template = getHibernateTemplate();
			Object id = template.save(object);;
			
			flush();
			return Integer.valueOf(id.toString());
			
		} catch (ConstraintViolationException e) {
			System.err.println(e);
			throw e;
			//throw new DatabaseException(e);
		} catch (JDBCException e) {
			System.err.println(e);
			throw e;
			//throw new DatabaseException(e);
		} catch (Exception e) {
			System.err.println(e);
			throw e;
			//throw new DatabaseException("Database Exception occurred.....", e);
		} 
		//return -1;
	}

	/**
	 * Generic update method.
	 */
	public void update(final T object) throws Exception {
		HibernateTemplate template = null;
		try {
			template = getHibernateTemplate();
			/*
             * Added to work with interfaces
             */
			template.update(object);
			//flush();
		} catch (ConstraintViolationException e) {
			System.err.println(e);
			throw new DatabaseException(e);
		} catch (JDBCException e) {
			System.err.println(e);
			throw new DatabaseException(e);
		} catch (Exception e) {
			System.err.println(e);
			throw new DatabaseException(e);
		} 
	}
	
	/**
	 * Generic Delete
	 */
	public void delete(final T object) {
		HibernateTemplate template = null;
		try {
			template = getHibernateTemplate();
			/*
             * Added to work with interfaces
             */
            template.delete(object);
			//flush();
		} catch (GenericJDBCException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		} 
	}
	
	public T load(Class<T> type, final Serializable objectId) {
		try {
			/* Delegates the save operation to the DefaultServiceManager */
			return (T) getHibernateTemplate().get(type, objectId);
			
		} catch (JDBCException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	/**
	 * Flushes the session committing all changes.
	 * 
	 */
	private void flush() {
		getSessionFactory().getCurrentSession().flush();
	}

}
