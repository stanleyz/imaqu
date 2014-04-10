/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service;

import java.util.List;

import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface AnswerManager extends GenericManager<Answer, String> {
	Answer createAnswer(Answer answer, User user);

	void setCandidate(String id, boolean b);

	void setLevel(String q, byte level);

	Answer voteThisAnswer(Answer answer, User user);

	List<Answer> getPaginatedNormalAnswers(String q, int firstResult,
			int maxResults);

	long getNormalAnswersCount(String q);

	public List<Answer> getAnswerByUser4Question(User user, Question question);

	void removeOwner(String[] userIds);

	List<Answer> findAnswers(Answer a, String order, int firstResult,
			int maxResults);
}
