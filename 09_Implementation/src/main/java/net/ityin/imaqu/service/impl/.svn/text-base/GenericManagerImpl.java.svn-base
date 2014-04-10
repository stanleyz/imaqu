/*
 * (c) OpenS Ltd. All Rights Reserved. 
 *
 */
package net.ityin.imaqu.service.impl;

import java.io.Serializable;

import net.ityin.imaqu.dao.GenericDao;
import net.ityin.imaqu.service.GenericManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * @author <a href="mailto:Phinux Zhang">Phinux.Zhang</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericManagerImpl<T, PK extends Serializable> implements
		GenericManager<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected Logger logger=LoggerFactory.getLogger(this.getClass()) ;

	/**
	 * GenericDao instance, set by constructor of this class
	 */
	protected GenericDao<T, PK> genericDao;

	/**
	 * Public constructor for creating a new GenericManagerImpl.
	 * 
	 * @param genericDao
	 *            the GenericDao to use for persistence
	 */
	public GenericManagerImpl(final GenericDao<T, PK> genericDao) {
		this.genericDao = genericDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public T get(PK id) {
		return genericDao.get(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean exists(PK id) {
		return genericDao.exists(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public T save(T object) {
		return genericDao.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		genericDao.remove(id);
	}
}
