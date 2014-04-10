package net.ityin.imaqu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the t_answer database table.
 * 
 */
@Entity
@Table(name = "T_ANSWER")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private String id;

	@Column(name = "BLOCKED")
	private boolean blocked;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BLOCKEDTILL")
	private Date blockedTill;

	@Column(name = "CANDIDATE")
	private boolean candidate;

	@Lob()
	@Column(name = "CONTENT")
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDAT")
	private Date createdAt;

	/**
	 * This is used to set the which level the answer is.<br />
	 * <br />
	 * 0 stands for NORMAL; <br/>
	 * 1 stands for BEST by the question author; <br/>
	 * 2 stands for BEST by intenet voting.
	 */
	@Column(name = "LEVEL")
	private byte level;

	/**
	 * This used to store how many tickets this answer gets.
	 */
	@Column(name = "TICKETS")
	private int tickets;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDAT")
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "QUESTION_ID", nullable = false)
	private Question question;

	public Answer() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isBlocked() {
		return this.blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Date getBlockedTill() {
		return this.blockedTill;
	}

	public void setBlockedTill(Date blockedTill) {
		this.blockedTill = blockedTill;
	}

	public boolean isCandidate() {
		return this.candidate;
	}

	public void setCandidate(boolean candidate) {
		this.candidate = candidate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public byte getLevel() {
		return this.level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public int getTickets() {
		return this.tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public Date getUpdatedat() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User User) {
		this.user = User;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question Question) {
		this.question = Question;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}