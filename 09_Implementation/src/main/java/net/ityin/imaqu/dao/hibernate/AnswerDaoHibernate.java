/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao.hibernate;

import java.util.List;

import net.ityin.imaqu.dao.AnswerDao;
import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.util.SystemConstant;

import org.apache.commons.lang.xwork.StringUtils;
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
@Repository(value = "answerDao")
@SuppressWarnings("unchecked")
public class AnswerDaoHibernate extends GenericDaoHibernate<Answer, String>
		implements AnswerDao {

	public AnswerDaoHibernate() {
		super(Answer.class);
	}

	public List<Answer> getPaginatedNormalAnswers(String q, int firstResult,
			int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Answer.class)
				.add(Restrictions.eq("question.id", q))
				.add(Restrictions.eq("level", SystemConstant.A_LEVEL_NORMAL))
				.addOrder(Order.desc("createdAt"));

		return findByCriteria(criteria, firstResult, maxResults);
	}

	public long getNormalAnswersCount(String q) {
		StringBuilder builder = new StringBuilder(
				"select count(*) from Answer as a where a.question.id=");
		builder.append(q);
		builder.append(" and a.level=");
		builder.append(SystemConstant.A_LEVEL_NORMAL);
		return (Long) (getHibernateTemplate().iterate(builder.toString())
				.next());
	}

	public List<Answer> getAnswerByUser4Question(User user, Question question) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Answer.class)
				.add(Restrictions.eq("question", question));
		if (user != null) {
			criteria.add(Restrictions.eq("user", user));
		}

		return findByCriteria(criteria, -1, -1);
	}

	public void removeOwner(String[] userIds) {
		StringBuilder sb = new StringBuilder(
				"update Answer a set a.user.id=null where ");
		for (String userId : userIds) {
			sb.append("a.user.id=");
			sb.append(userId);
			sb.append(" or ");
		}

		getHibernateTemplate().bulkUpdate(sb.substring(0, sb.length() - 4));
	}

	public List<Answer> findAnswers(Answer a, String order, int firstResult,
			int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Answer.class);
		String s;
		if (a.getUser() != null && (s = a.getUser().getNickname()).length() > 0) {
			criteria.createCriteria("user", "u").add(
					Restrictions.like("u.nickname", s, MatchMode.ANYWHERE));
		}

		if (a.getQuestion() != null
				&& (s = a.getQuestion().getContent()).length() > 0) {
			criteria.createCriteria("question", "q").add(
					Restrictions
							.disjunction()
							.add(Restrictions.like("q.title", s,
									MatchMode.ANYWHERE))
							.add(Restrictions.like("q.content", s,
									MatchMode.ANYWHERE))
							.add(Restrictions.like("q.supplyment", s,
									MatchMode.ANYWHERE)));
		}

		if (a.getContent() != null && (s = a.getContent()).length() > 0) {
			criteria.add(Restrictions.like("content", s, MatchMode.ANYWHERE));
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
}
