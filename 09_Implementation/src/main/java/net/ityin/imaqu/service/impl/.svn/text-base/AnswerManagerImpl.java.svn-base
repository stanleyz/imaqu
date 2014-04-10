/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service.impl;

import java.util.Date;
import java.util.List;

import net.ityin.imaqu.dao.AnswerDao;
import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.service.AnswerManager;
import net.ityin.imaqu.service.QuestionManager;
import net.ityin.imaqu.service.UserManager;
import net.ityin.imaqu.util.SystemConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Service(value = "answerManager")
public class AnswerManagerImpl extends GenericManagerImpl<Answer, String>
		implements AnswerManager {
	private AnswerDao answerDao;
	@Autowired
	private QuestionManager questionManager;
	@Autowired
	private UserManager userManager;

	@Autowired
	public AnswerManagerImpl(AnswerDao answerDao) {
		super(answerDao);
		this.answerDao = answerDao;
	}

	public Answer createAnswer(Answer answer, User user) {
		Question q = questionManager.get(answer.getQuestion().getId());
		if (q.getStatus() == SystemConstant.Q_STATUS_OPEN) {
			List<Answer> answers = answerDao.getAnswerByUser4Question(user,
					answer.getQuestion());
			if (answers.size() > 0) {
				Answer a = answer;
				answer = answerDao.get(answer.getId());
				answer.setUpdatedAt(new Date());
				answer.setContent(a.getContent());
			} else {
				answer.setCreatedAt(new Date());

				user = userManager.get(user.getId());
				user.setScore(user.getScore() + SystemConstant.USER_SCORE_TWO);
				userManager.save(user);
				userManager.setAuthentication(user);

				answer.setUser(user);
			}

			return answerDao.save(answer);
		}

		return null;
	}

	public void setCandidate(String id, boolean b) {
		Answer answer = answerDao.get(id);
		answer.setCandidate(b);
		answerDao.save(answer);
	}

	public void setLevel(String q, byte level) {
		Answer a = answerDao.get(q);
		a.setLevel(level);

		if (level != SystemConstant.A_LEVEL_NORMAL) {
			User u = a.getUser();
			if (u != null) {
				u.setScore(u.getScore() + a.getQuestion().getReward()
						+ SystemConstant.USER_SCORE_TEN);
				userManager.save(u);
			}
		}
		answerDao.save(a);
	}

	public Answer voteThisAnswer(Answer answer, User user) {
		answer = answerDao.get(answer.getId());
		Question q = answer.getQuestion();
		if (q.getVoters().contains(user)) {
			return null;
		} else {
			user.setScore(user.getScore() + SystemConstant.USER_SCORE_ONE);
			userManager.save(user);
			userManager.setAuthentication(user);
			q.getVoters().add(user);
			questionManager.save(q);
		}
		answer.setTickets(answer.getTickets() + 1);
		answerDao.save(answer);
		return answer;
	}

	public List<Answer> getPaginatedNormalAnswers(String q, int firstResult,
			int maxResults) {
		if (maxResults == 0) {
			maxResults = SystemConstant.A_PER_PAGE;
		}
		return answerDao.getPaginatedNormalAnswers(q, firstResult, maxResults);
	}

	public long getNormalAnswersCount(String q) {
		return answerDao.getNormalAnswersCount(q);
	}

	@Override
	public void remove(String id) {
		User u = answerDao.get(id).getUser();
		u.setScore(u.getScore() - SystemConstant.USER_SCORE_TWO);
		userManager.save(u);
		super.remove(id);
	}

	public List<Answer> getAnswerByUser4Question(User user, Question question) {
		return answerDao.getAnswerByUser4Question(user, question);
	}

	public void removeOwner(String[] userIds) {
		answerDao.removeOwner(userIds);
	}

	public List<Answer> findAnswers(Answer a, String order, int firstResult,
			int maxResults) {
		return answerDao.findAnswers(a, order, firstResult, maxResults);
	}
}
