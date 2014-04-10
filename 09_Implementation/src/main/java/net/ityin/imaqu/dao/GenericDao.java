/*
 * (c) OpenS Ltd. All Rights Reserved. 
 *
 */
package net.ityin.imaqu.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * 
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for
 * your domain objects.
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericDao<T, PK extends Serializable> {
	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	T get(PK id);

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * Clear the hibernate session cache first.
	 * 
	 * @param id
	 * @param clear
	 * @return
	 */
	T get(PK id, boolean clear);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(PK id);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the persisted object
	 */
	T save(T object);

	/**
	 * Some times we need other method from {@link HibernateTemplate} like save,
	 * saveOrUpdate, merge, save.
	 * 
	 * @return
	 */
	HibernateTemplate getHibernateTemplate();

	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to remove
	 */
	void remove(PK id);

	/**
	 * Get a object from the named query.
	 * 
	 * @param queryName
	 *            name of stored query
	 * @param values
	 *            parameters as an array
	 * @return object list
	 */
	List<T> findByNamedQuery(String queryName, Object[] values);

	/**
	 * Find a paginated list of objects by criteria.
	 * 
	 * @param criteria
	 * @param firstResult
	 *            Row number the result starts from.
	 * @param maxResults
	 *            Maximum results count.
	 * @return Paginated list of the object.
	 */
	List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults);
}
