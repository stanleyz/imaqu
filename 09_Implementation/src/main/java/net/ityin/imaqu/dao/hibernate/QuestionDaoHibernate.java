/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao.hibernate;

import java.util.List;

import net.ityin.imaqu.dao.QuestionDao;
import net.ityin.imaqu.model.Question;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@SuppressWarnings("unchecked")
@Repository(value = "questionDao")
public class QuestionDaoHibernate extends GenericDaoHibernate<Question, String>
		implements QuestionDao {

	public QuestionDaoHibernate() {
		super(Question.class);
	}

	public List<Question> getQuestions(Byte status, Boolean recommended,
			List<String> categoryIds, String order, int firstResult,
			int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Question.class);
		if (status != null) {
			criteria.add(Restrictions.eq("status", status.byteValue()));
		}
		if (recommended != null) {
			criteria.add(Restrictions.eq("recommended",
					recommended.booleanValue()));
		}
		if (categoryIds != null && categoryIds.size() > 0) {
			Disjunction junction = Restrictions.disjunction();
			for (String id : categoryIds) {
				junction.add(Restrictions.eq("category.id", id));
			}
			criteria.add(junction);
		}
		if (maxResults == 0) {
			criteria.setProjection(Projections.projectionList().add(
					Projections.rowCount()));
		} else {
			if (order != null && order.length() > 0) {
				String[] orderProperties = StringUtils.split(order, "-");
				String[] tmp;
				for (String ps : orderProperties) {
					tmp = StringUtils.split(ps, "_");
					Property p = Property.forName(tmp[0]);
					if (tmp[1].equals("desc")) {
						criteria.addOrder(p.desc());
					} else {
						criteria.addOrder(p.asc());
					}
				}
			}
		}

		return findByCriteria(criteria, firstResult, maxResults);
	}

	public void removeOwner(String[] userIds) {
		StringBuilder sb = new StringBuilder(
				"update Question q set q.user.id=null where ");
		for (String userId : userIds) {
			sb.append("q.user.id=");
			sb.append(userId);
			sb.append(" or ");
		}
		getHibernateTemplate().bulkUpdate(sb.substring(0, sb.length() - 4));
	}

	public List<Question> findQuestions(Question q, String order,
			int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Question.class);
		if (q.getUser() != null && q.getUser().getNickname().length() > 0) {
			criteria.createCriteria("user", "u").add(
					Restrictions.like("u.nickname", q.getUser().getNickname(),
							MatchMode.ANYWHERE));
		}

		if (q.getCategory() != null) {
			criteria.add(Restrictions
					.eq("category.id", q.getCategory().getId()));
		}

		if (q.getContent() != null && q.getContent().length() > 0) {
			criteria.add(Restrictions
					.disjunction()
					.add(Restrictions.like("title", q.getContent(),
							MatchMode.ANYWHERE))
					.add(Restrictions.like("content", q.getContent(),
							MatchMode.ANYWHERE))
					.add(Restrictions.like("supplyment", q.getContent(),
							MatchMode.ANYWHERE)));
		}

		if (q.getReward() != null) {
			if (q.getReward() > 0) {
				criteria.add(Restrictions.ge("reward", q.getReward()));
			} else {
				criteria.add(Restrictions.le("reward", q.getReward()));
			}
		}

		if (q.getStatus() != null) {
			criteria.add(Restrictions.eq("status", q.getStatus()));
		}

		if (q.getRecommended() != null) {
			criteria.add(Restrictions.eq("recommended", q.getRecommended()));
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
