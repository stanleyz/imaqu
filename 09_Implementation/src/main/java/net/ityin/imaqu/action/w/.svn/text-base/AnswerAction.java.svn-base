/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action.w;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import net.ityin.imaqu.action.GenericAction;
import net.ityin.imaqu.model.Answer;
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
public class AnswerAction extends GenericAction {
	private static final long serialVersionUID = 2813823749922547720L;
	private Answer answer;
	private InputStream inputStream;
	@Autowired
	private AnswerManager answerManager;
	private List<Answer> normalAnswers;
	private long normalAnswersCount;
	private String questionId;
	private String firstResult = "0";
	private String maxResults = String.valueOf(SystemConstant.A_PER_PAGE);

	public String saveAnswer() {
		if (answer.getContent().length() == 0) {
			inputStream = new ByteArrayInputStream(INPUT.getBytes());
			return INPUT;
		}

		answer = answerManager.createAnswer(answer, getAuthenticatedUser());
		return SUCCESS;
	}

	public String voteThisAnswer() {
		answer = answerManager.voteThisAnswer(answer, getAuthenticatedUser());

		if (answer == null) {
			inputStream = new ByteArrayInputStream(INPUT.getBytes());
		} else {
			inputStream = new ByteArrayInputStream(String.valueOf(
					answer.getTickets()).getBytes());
		}
		return SUCCESS;
	}

	public String remove() {
		answerManager.remove(answer.getId());
		inputStream = new ByteArrayInputStream(SUCCESS.getBytes());
		return SUCCESS;
	}

	public String getPaginatedAnswers() {
		normalAnswers = answerManager.getPaginatedNormalAnswers(questionId,
				Integer.valueOf(firstResult), Integer.valueOf(maxResults));

		return SUCCESS;
	}

	public String getNormalACount() {
		if (questionId != null) {
			normalAnswersCount = answerManager
					.getNormalAnswersCount(questionId);
		}
		return SUCCESS;
	}

	public String checkAnswer4Question() {
		normalAnswersCount = answerManager.getAnswerByUser4Question(
				getAuthenticatedUser(), answer.getQuestion()).size();
		return SUCCESS;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public List<Answer> getNormalAnswers() {
		return normalAnswers;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public void setMaxResults(String maxResults) {
		this.maxResults = maxResults;
	}

	public long getNormalAnswersCount() {
		return normalAnswersCount;
	}
}
