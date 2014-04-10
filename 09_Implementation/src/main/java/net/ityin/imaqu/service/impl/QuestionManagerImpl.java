/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service.impl;

import java.security.acl.NotOwnerException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.ityin.imaqu.dao.QuestionDao;
import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Category;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.service.AnswerManager;
import net.ityin.imaqu.service.CategoryManager;
import net.ityin.imaqu.service.QuestionManager;
import net.ityin.imaqu.service.UserManager;
import net.ityin.imaqu.util.SystemConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Service(value = "questionManager")
public class QuestionManagerImpl extends GenericManagerImpl<Question, String>
		implements QuestionManager {
	private QuestionDao questionDao;
	@Autowired
	private CategoryManager categoryManager;
	@Autowired
	private AnswerManager answerManager;
	@Autowired
	private UserManager userManager;

	@Autowired
	public QuestionManagerImpl(QuestionDao questionDao) {
		super(questionDao);
		this.questionDao = questionDao;
	}

	public Question createQuestion(Question q, User user) {
		Calendar c = Calendar.getInstance();
		q.setCreatedAt(c.getTime());

		q.setStatus(SystemConstant.Q_STATUS_OPEN);
		c.add(Calendar.DAY_OF_YEAR, SystemConstant.Q_OPEN_PERIOD);
		q.setDeadline(c.getTime());
		q.setBlocked(false);
		q.setRecommended(false);
		q.setUser(user);

		if (q.getCategory().getId().length() == 0) {
			q.setCategory(categoryManager.getD());
		}

		user.setScore(user.getScore() - q.getReward());
		userManager.setAuthentication(user);
		userManager.save(user);
		return questionDao.save(q);
	}

	public Question addSupplyment(Question question, User user)
			throws NotOwnerException {
		Question q = questionDao.get(question.getId());
		if (!user.equals(q.getUser())) {
			throw new NotOwnerException();
		}
		q.setSupplyment(question.getSupplyment());

		return questionDao.save(q);
	}

	public Question addReward(Question question, User user)
			throws NotOwnerException {
		Question q = questionDao.get(question.getId());
		if (!user.equals(q.getUser())) {
			throw new NotOwnerException();
		}

		int r = question.getReward() + q.getReward();
		if (question.getReward() > q.getUser().getScore()
				|| r > SystemConstant.MAX_SCORE_PER_QUESTION) {
			return null;
		} else {
			q.setReward(r);

			User u = q.getUser();
			u.setScore(u.getScore() - question.getReward());
			userManager.save(u);
			userManager.setAuthentication(u);
			return questionDao.save(q);
		}
	}

	public void transferToPoll(Question q, User user, String[] ids)
			throws NotOwnerException {
		q = questionDao.get(q.getId());
		if (!user.equals(q.getUser())) {
			throw new NotOwnerException();
		}
		if (q.getStatus() != SystemConstant.Q_STATUS_OPEN) {
			return;
		}

		Calendar c = Calendar.getInstance();
		c.roll(Calendar.DAY_OF_YEAR, SystemConstant.Q_POLL_PERIOD);
		q.setDeadline(c.getTime());
		q.setStatus(SystemConstant.Q_STATUS_POLL);
		questionDao.save(q);

		for (String id : ids) {
			answerManager.setCandidate(id, true);
		}
	}

	public void setBestAnswer(Question question, User user, String q)
			throws NotOwnerException {
		question = questionDao.get(question.getId());
		if (!user.equals(question.getUser())) {
			throw new NotOwnerException();
		}
		question.setStatus(SystemConstant.Q_STATUS_CLOSED);
		questionDao.save(question);

		answerManager.setLevel(q, SystemConstant.A_LEVEL_BEST);
	}

	@Override
	public Question get(String id) {
		Question q = super.get(id);
		if (q.getStatus() != SystemConstant.Q_STATUS_CLOSED
				&& q.getDeadline().compareTo(new Date()) <= 0) {
			if (q.getStatus() == SystemConstant.Q_STATUS_POLL) {
				Answer bestAnswer = null;
				int tickets = 0;
				for (Answer a : answerManager.getPaginatedNormalAnswers(id, -1,
						-1)) {
					if (a.getTickets() > tickets) {
						tickets = a.getTickets();
						bestAnswer = a;
					}
				}

				if (bestAnswer != null) {
					answerManager.setLevel(bestAnswer.getId(),
							SystemConstant.A_LEVEL_BEST_VOTED);

					q.getBestAnswers().add(bestAnswer);
				}
			}

			q.setStatus(SystemConstant.Q_STATUS_CLOSED);

			questionDao.save(q);
		}

		return q;
	}

	public void noGoodA(Question question, User user) throws NotOwnerException {
		question = questionDao.get(question.getId());
		if (!user.equals(question.getUser())) {
			throw new NotOwnerException();
		}
		question.setStatus(SystemConstant.Q_STATUS_CLOSED);

		User u = question.getUser();
		user.setScore(user.getScore() + question.getReward());
		userManager.save(u);

		questionDao.save(question);
	}

	public List<Question> getQuestions(Byte status, Boolean recommended,
			String categoryId, String order, int firstResult, int maxResults) {
		List<String> categoryIds = null;
		if (categoryId != null) {
			categoryIds = categoryManager.findChildrenIds(categoryId);
		}
		return questionDao.getQuestions(status, recommended, categoryIds,
				order, firstResult, maxResults);
	}

	public void removeOwner(String[] userIds) {
		questionDao.removeOwner(userIds);
	}

	public List<Question> findQuestions(Question q, String order,
			int firstResult, int maxResults) {
		return questionDao.findQuestions(q, order, firstResult, maxResults);
	}

	public void switchRecommend(String[] ids) {
		Question q;
		for (String id : ids) {
			q = questionDao.get(id);
			q.setRecommended(!q.getRecommended());
			questionDao.save(q);
		}
	}

	public void resetCategory(String[] ids, String cid) {
		Question q;
		Category c=new Category();
		c.setId(cid);
		for(String id: ids) {
			q=questionDao.get(id);
			q.setCategory(c);
			questionDao.save(q);
		}
	}
}
