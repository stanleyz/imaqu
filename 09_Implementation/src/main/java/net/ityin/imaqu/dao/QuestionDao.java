/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao;

import java.util.List;

import net.ityin.imaqu.model.Question;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface QuestionDao extends GenericDao<Question, String> {
	/**
	 * Get a list of questions.
	 * 
	 * The results are different depends on the value of <code>maxResults</code>
	 * . If <code>maxResults</code> equals -1, the function will query all the
	 * data from the database; if equals 0, the function will return the rows
	 * count of the data; if great than 0, it will return the request result
	 * collection.
	 * 
	 * @param status
	 * @param recommended
	 * @param categoryIds
	 * @param order
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<Question> getQuestions(Byte status, Boolean recommended,
			List<String> categoryIds, String order, int firstResult,
			int maxResults);

	public void removeOwner(String[] userIds);

	public List<Question> findQuestions(Question q, String order,
			int firstResult, int maxResults);
}
