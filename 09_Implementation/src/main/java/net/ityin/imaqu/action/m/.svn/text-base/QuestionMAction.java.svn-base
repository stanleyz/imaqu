/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action.m;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import net.ityin.imaqu.action.GenericAction;
import net.ityin.imaqu.model.Category;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.service.QuestionManager;
import net.ityin.imaqu.util.SystemConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Scope("prototype")
@Controller
public class QuestionMAction extends GenericAction {
	private static final long serialVersionUID = -739586641215598929L;
	private List<Question> questions;
	@Autowired
	private QuestionManager questionManager;
	private InputStream inputStream;
	private String field;
	private String order;
	private int first = 0;
	private int max = SystemConstant.MAX_RESULTS_2TEN;
	private String owner;
	private String content;
	private String status;
	private String cid;
	private String scope;
	private String reward;

	public String findQuestions() {
		Question q = new Question();
		if (owner != null && owner.trim().length() > 0
				&& (!owner.trim().equals("*"))) {
			User u = new User();
			u.setNickname(owner.trim());
			q.setUser(u);
		}

		if (cid != null && cid.trim().length() > 0) {
			Category c = new Category();
			c.setId(cid);
			q.setCategory(c);
		}

		if (content != null && content.trim().length() > 0) {
			q.setContent(content.trim());
		}

		if (reward != null && reward.trim().length() > 0) {
			int r = 9999;
			try {
				r = Integer.parseInt(reward.trim());
			} catch (Exception e) {
			}
			if (scope.equals("-11")) {
				r = 0 - r;
			}
			q.setReward(r);
		}

		if (status != null) {
			if (status.equals("0")) {
				q.setStatus(SystemConstant.Q_STATUS_OPEN);
			} else if (status.equals("1")) {
				q.setStatus(SystemConstant.Q_STATUS_POLL);
			} else if (status.equals("2")) {
				q.setStatus(SystemConstant.Q_STATUS_CLOSED);
			} else if (status.equals("999")) {
				q.setRecommended(true);
			}
		}

		StringBuilder s = new StringBuilder();
		if (field != null && field.trim().length() > 0) {
			s.append(field);
			s.append("|");
			s.append(order);
		}

		questions = questionManager.findQuestions(q, s.toString(), first, max);
		return SUCCESS;
	}

	public String deleteQuestions() {
		String[] ids = null;
		if (owner != null && owner.length() > 0) {
			ids = owner.split(",");
		}

		if (ids != null) {
			for (String id : ids) {
				questionManager.remove(id);
			}
		}

		return SUCCESS;
	}

	public String switchRecommend() {
		String[] ids = null;
		if (owner != null && owner.length() > 0) {
			ids = owner.split(",");
		}

		if (ids != null) {
			questionManager.switchRecommend(ids);
		}

		return SUCCESS;
	}

	public String resetCategory() {
		String[] ids = null;
		if (owner != null && owner.length() > 0) {
			ids = owner.split(",");
		}

		if (ids != null && cid != null) {
			questionManager.resetCategory(ids, cid);
		}

		return SUCCESS;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getFirst() {
		return first;
	}

	public int getMax() {
		return max;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public InputStream getInputStream() {
		if (inputStream == null) {
			inputStream = new ByteArrayInputStream(SUCCESS.getBytes());
		}
		return inputStream;
	}
}
