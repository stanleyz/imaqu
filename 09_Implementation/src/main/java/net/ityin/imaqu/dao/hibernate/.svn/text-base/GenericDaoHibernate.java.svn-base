/*
 * (c) OpenS Ltd. All Rights Reserved. 
 *
 */
package net.ityin.imaqu.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import net.ityin.imaqu.dao.GenericDao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements
		GenericDao<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private Class<T> persistentClass;
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;

	/**
	 * Constructor that takes in a class to see which type of entity to persist
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDaoHibernate(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * {@inheritDoc}
	 */
	public T get(PK id) {
		T entity = (T) hibernateTemplate.get(this.persistentClass, id);

		if (entity == null) {
			logger.warn("Uh oh, '" + this.persistentClass
					+ "' object with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(this.persistentClass, id);
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean exists(PK id) {
		T entity = (T) hibernateTemplate.get(this.persistentClass, id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	public T save(T object) {
		object = hibernateTemplate.merge(object);
		hibernateTemplate.flush();
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		hibernateTemplate.delete(this.get(id));
		hibernateTemplate.flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findByNamedQuery(String queryName, Object[] values) {
		return hibernateTemplate.findByNamedQuery(queryName, values);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) {
		return this.getHibernateTemplate().findByCriteria(criteria,
				firstResult, maxResults);
	}

	public T get(PK id, boolean clear) {
		if(clear) {
			getHibernateTemplate().clear();
		}
		return get(id);
	}
}
