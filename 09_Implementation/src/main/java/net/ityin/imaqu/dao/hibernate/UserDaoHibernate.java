/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao.hibernate;

import java.util.Date;
import java.util.List;

import net.ityin.imaqu.dao.UserDao;
import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.util.SystemConstant;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@SuppressWarnings("unchecked")
@Repository(value = "userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, String>
		implements UserDao {
	public UserDaoHibernate() {
		super(User.class);
	}

	public User findByNickname(String nickname) {
		Object[] params = new Object[] { nickname };
		List<User> users = this.getHibernateTemplate().findByNamedQuery(
				"findUserByNickname", params);
		if (users.size() != 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	public List<User> getTopAsker(Date date, int maxResults) {
		// HQL method.
		// String hql =
		// "select u.nickname, count(q.id) from User u, Question q where q.user.id=u.id";
		// return getHibernateTemplate().find(hql);

		// Criteria method from table questions and join table users.
		// DetachedCriteria criteria = DetachedCriteria
		// .forClass(Question.class, "q")
		// .add(Restrictions.ge("q.createdAt", date))
		// .createCriteria("user", "u")
		// .setProjection(
		// Projections
		// .projectionList()
		// .add(Property.forName("u.id"))
		// .add(Projections.groupProperty("u.nickname"))
		// .add(Projections.count("q.id"),
		// "questionsCount"))
		// .addOrder(Order.desc("questionsCount"));

		// Criteria method from table user and join table question.
		DetachedCriteria criteria = DetachedCriteria
				.forClass(User.class, "u")
				.createCriteria("questions", "q",
						CriteriaSpecification.LEFT_JOIN)
				.add(Restrictions.ge("q.createdAt", date))
				.setProjection(
						Projections
								.projectionList()
								.add(Property.forName("u.id"))
								.add(Projections.groupProperty("u.nickname"))
								.add(Projections.count("q.id"),
										"questionsCount"))
				.addOrder(Order.desc("questionsCount"));
		return findByCriteria(criteria, 0, maxResults);

	}

	public List<User> getTopReplier(Date date, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(User.class, "u")
				.createCriteria("answers", "a", CriteriaSpecification.LEFT_JOIN)
				.add(Restrictions.ge("a.createdAt", date))
				.setProjection(
						Projections.projectionList()
								.add(Property.forName("u.id"))
								.add(Projections.groupProperty("u.nickname"))
								.add(Projections.count("a.id"), "answersCount"))
				.addOrder(Order.desc("answersCount"));
		return findByCriteria(criteria, 0, maxResults);
	}

	@SuppressWarnings("rawtypes")
	public List getQuestionsByUser(User user, Byte status, Boolean recommended,
			int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Question.class);
		criteria.add(Restrictions.eq("user", user));
		if (status != null) {
			criteria.add(Restrictions.eq("status", status.byteValue()));
		}
		if (recommended != null) {
			criteria.add(Restrictions.eq("recommended",
					recommended.booleanValue()));
		}
		if (maxResults == 0) {
			criteria.setProjection(Projections.projectionList().add(
					Projections.rowCount()));
		} else {
			criteria.addOrder(Order.desc("createdAt"));
		}
		return findByCriteria(criteria, firstResult, maxResults);
	}

	@SuppressWarnings("rawtypes")
	public List getAnswersByUser(User user, Byte status, int firstResult,
			int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Answer.class);
		criteria.add(Restrictions.eq("user", user));
		if (status != null) {
			criteria.add(Restrictions.eq("level", status.byteValue()));
		}
		if (maxResults == 0) {
			criteria.setProjection(Projections.projectionList().add(
					Projections.rowCount()));
		} else {
			criteria.addOrder(Order.desc("createdAt"));
		}
		return findByCriteria(criteria, firstResult, maxResults);
	}

	public List<User> getTopUsers(int orderProperty, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if (orderProperty == SystemConstant.USERS_ORDER_A) {
			criteria.addOrder(Order.desc("answersCount"));
		} else if (orderProperty == SystemConstant.USERS_ORDER_Q) {
			criteria.addOrder(Order.desc("questionsCount"));
		} else {
			criteria.addOrder(Order.desc("score"));
		}
		return findByCriteria(criteria, 0, maxResults);
	}

	public int getUserOrder(User u, int orderProperty) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if (orderProperty == SystemConstant.USERS_ORDER_Q) {
			criteria.add(
					Restrictions.gt("questionsCount", u.getQuestionsCount()))
					.setProjection(
							Projections.projectionList()
									.add(Projections
											.countDistinct("questionsCount")));
		} else if (orderProperty == SystemConstant.USERS_ORDER_A) {
			criteria.add(Restrictions.gt("answersCount", u.getAnswersCount()))
					.setProjection(
							Projections.projectionList().add(
									Projections.countDistinct("answersCount")));
		} else {
			criteria.add(Restrictions.gt("score", u.getScore())).setProjection(
					Projections.countDistinct("score"));
		}
		return (Integer) findByCriteria(criteria, -1, -1).get(0) + 1;
	}

	public List<User> getUsersByCondition(User user, String order,
			int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if (user.getNickname() != null) {
			criteria.add(Restrictions.like("nickname", user.getNickname(),
					MatchMode.ANYWHERE));
		}
		if (user.getBlockedTill() != null) {
			criteria.add(Restrictions.eq("blocked", user.isBlocked()));
		}
		if (maxResults == 0) {
			criteria.setProjection(Projections.projectionList().add(
					Projections.rowCount()));
		} else {
			if (order != null && order.length() > 0) {
				String[] tmp = StringUtils.split(order, "|");
				Property p = Property.forName(tmp[0]);
				if (tmp[1].equals("desc")) {
					criteria.addOrder(p.desc());
				} else {
					criteria.addOrder(p.asc());
				}
			}
		}
		return findByCriteria(criteria, firstResult, maxResults);
	}

	/**
	 * Set user state to active or not.
	 * 
	 * @param id
	 * @param time
	 * @param active
	 *            true means active, otherwise, blocked
	 */
	public void switchUserState(User user, Date time, boolean active) {
		if (active) {
			user.setBlocked(false);
		} else {
			user.setBlocked(true);
			user.setBlockedTill(time);
		}

		save(user);
	}
}