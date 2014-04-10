/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action.w;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.acl.NotOwnerException;
import java.util.ArrayList;
import java.util.List;

import net.ityin.imaqu.action.GenericAction;
import net.ityin.imaqu.model.Question;
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
@Controller
@Scope("prototype")
public class QuestionAction extends GenericAction {
	private static final long serialVersionUID = 2813823749922547720L;
	private Question question = new Question();
	private InputStream inputStream;
	private String cid;
	private String order;
	private Byte status = null;
	private Boolean recommended = null;
	private int first = 0;
	private int max = SystemConstant.MAX_RESULTS_2TEN;
	private List<Question> questions = new ArrayList<Question>();
	@Autowired
	private QuestionManager questionManager;
	/*
	 * This is used by the http request to set the question id.
	 */
	private String q;

	public String saveQuestion() {
		if (question.getTitle().length() == 0) {
			return INPUT;
		}

		question = questionManager.createQuestion(question,
				getAuthenticatedUser());
		return SUCCESS;
	}

	public String getQuestionById() {
		question = questionManager.get(q);

		return SUCCESS;
	}

	public String addSupplyment() {
		if (question.getSupplyment().length() == 0) {
			inputStream = new ByteArrayInputStream(INPUT.getBytes());
			return INPUT;
		}

		try {
			question = questionManager.addSupplyment(question,
					getAuthenticatedUser());
		} catch (NotOwnerException e) {
			return LOGIN;
		}

		return SUCCESS;
	}

	public String addReward() {
		try {
			question = questionManager.addReward(question,
					getAuthenticatedUser());
		} catch (NotOwnerException e) {
			return LOGIN;
		}

		if (question == null) {
			inputStream = new ByteArrayInputStream(INPUT.getBytes());
		} else {
			inputStream = new ByteArrayInputStream(String.valueOf(
					question.getReward()).getBytes());
		}

		return SUCCESS;
	}

	public String transferToPoll() {
		if (q == null || q.length() == 0) {
			inputStream = new ByteArrayInputStream(INPUT.getBytes());
		} else {
			try {
				questionManager.transferToPoll(question,
						getAuthenticatedUser(), q.split(","));
			} catch (NotOwnerException e) {
				return LOGIN;
			}
			inputStream = new ByteArrayInputStream(SUCCESS.getBytes());
		}

		return SUCCESS;
	}

	public String setBestAnswer() {
		try {
			questionManager.setBestAnswer(question, getAuthenticatedUser(), q);
		} catch (NotOwnerException e) {
			return LOGIN;
		}

		inputStream = new ByteArrayInputStream(SUCCESS.getBytes());
		return SUCCESS;
	}

	public String noGoodA() {
		try {
			questionManager.noGoodA(question, getAuthenticatedUser());
		} catch (NotOwnerException e) {
			return LOGIN;
		}

		inputStream = new ByteArrayInputStream(SUCCESS.getBytes());
		return SUCCESS;
	}

	public String getQuestionsByCondition() {
		questions = questionManager.getQuestions(status, recommended, cid,
				order, first, max);
		return SUCCESS;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
}
