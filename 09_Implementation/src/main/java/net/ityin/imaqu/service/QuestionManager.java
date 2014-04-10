/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service;

import java.security.acl.NotOwnerException;
import java.util.List;

import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface QuestionManager extends GenericManager<Question, String> {
	public Question createQuestion(Question q, User user);

	public Question addSupplyment(Question question, User user)
			throws NotOwnerException;

	public Question addReward(Question question, User user)
			throws NotOwnerException;

	public void transferToPoll(Question question, User user, String[] ids)
			throws NotOwnerException;

	public void setBestAnswer(Question question, User user, String q)
			throws NotOwnerException;

	public void noGoodA(Question question, User user) throws NotOwnerException;

	public List<Question> getQuestions(Byte status, Boolean recommended,
			String categoryId, String order, int firstResult, int maxResults);

	public void removeOwner(String[] userIds);

	public List<Question> findQuestions(Question q, String order, int firstResult,
			int maxResults);

	public void switchRecommend(String[] ids);

	public void resetCategory(String[] ids, String cid);
}
