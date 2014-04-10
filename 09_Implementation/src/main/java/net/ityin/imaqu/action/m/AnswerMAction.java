/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action.m;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import net.ityin.imaqu.action.GenericAction;
import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.service.AnswerManager;
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
@Controller
@Scope("prototype")
public class AnswerMAction extends GenericAction {
	private static final long serialVersionUID = 1L;
	private String owner;
	private String qcontent;
	private String content;
	private String field;
	private String order;
	private int first = 0;
	private int max = SystemConstant.MAX_RESULTS_2TEN;
	private InputStream inputStream;

	private List<Answer> answers;
	@Autowired
	private AnswerManager answerManager;

	public String findAnswers() {
		Answer a = new Answer();
		if (owner != null && owner.trim().length() > 0
				&& (!owner.trim().equals("*"))) {
			User u = new User();
			u.setNickname(owner.trim());
			a.setUser(u);
		}

		if (qcontent != null && qcontent.trim().length() > 0) {
			Question q = new Question();
			q.setContent(qcontent);
			a.setQuestion(q);
		}

		if (content != null && content.trim().length() > 0) {
			a.setContent(content.trim());
		}

		StringBuilder s = new StringBuilder();
		if (field != null && field.trim().length() > 0) {
			s.append(field);
			s.append("|");
			s.append(order);
		}

		answers = answerManager.findAnswers(a, s.toString(), first, max);
		return SUCCESS;
	}

	public String deleteAnswers() {
		String[] ids = null;
		if (owner != null && owner.length() > 0) {
			ids = owner.split(",");
		}

		if (ids != null) {
			for (String id : ids) {
				answerManager.remove(id);
			}
		}

		return SUCCESS;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getQcontent() {
		return qcontent;
	}

	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public InputStream getInputStream() {
		if (inputStream == null) {
			inputStream = new ByteArrayInputStream(SUCCESS.getBytes());
		}
		return inputStream;
	}
}
