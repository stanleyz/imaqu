package net.ityin.imaqu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.ityin.imaqu.util.SystemConstant;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

/**
 * The persistent class for the t_question database table.
 * 
 */
@Entity
@Table(name = "T_QUESTION")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private String id;

	@Column(name = "BLOCKED")
	private Boolean blocked;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BLOCKEDTILL")
	private Date blockedTill;

	@Column(name = "TITLE", nullable = false, length = 140)
	private String title;

	@Lob()
	@Column(name = "CONTENT")
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDAT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEADLINE")
	private Date deadline;

	@Column(name = "DIGCOUNT")
	private Integer digCount;

	@Column(name = "RECOMMENDED")
	private Boolean recommended;

	@Column(name = "REWARD")
	private Integer reward;

	/**
	 * This is the status of the this question.<br />
	 * <br />
	 * 0 represents OPEN; <br />
	 * 1 stands for CLOSED; <br />
	 * 2 stands for IN POOL.
	 */
	@Column(name = "STATUS")
	private Byte status;

	@Lob()
	@Column(name = "SUPPLYMENT")
	private String supplyment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDAT")
	private Date updatedAt;

	@Column(name = "VIEWEDCOUNT")
	private Integer viewedCount;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	@Where(clause = "level!=" + SystemConstant.A_LEVEL_NORMAL)
	private List<Answer> bestAnswers;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "question", fetch = FetchType.LAZY)
	private List<Answer> answers;

	@Formula("(select count(*) from T_ANSWER a where a.QUESTION_ID=id)")
	private int answersCount;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@ManyToMany(cascade = { CascadeType.REMOVE }, targetEntity = User.class)
	@JoinTable(name = "T_USER_VOTING", joinColumns = @JoinColumn(name = "QUESTION_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	private Set<User> voters = new HashSet<User>();

	public Question() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getBlocked() {
		return this.blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public Date getBlockedTill() {
		return this.blockedTill;
	}

	public void setBlockedTill(Date blockedTill) {
		this.blockedTill = blockedTill;
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

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Integer getDigCount() {
		return this.digCount;
	}

	public void setDigCount(Integer digCount) {
		this.digCount = digCount;
	}

	public Boolean getRecommended() {
		return this.recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	public Integer getReward() {
		return this.reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getSupplyment() {
		return this.supplyment;
	}

	public void setSupplyment(String supplyment) {
		this.supplyment = supplyment;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getViewedCount() {
		return this.viewedCount;
	}

	public void setViewedCount(Integer viewedCount) {
		this.viewedCount = viewedCount;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User User) {
		this.user = User;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category Category) {
		this.category = Category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Answer> getBestAnswers() {
		return bestAnswers;
	}

	public void setBestAnswers(List<Answer> bestAnswers) {
		this.bestAnswers = bestAnswers;
	}

	public Set<User> getVoters() {
		return voters;
	}

	public void setVoters(Set<User> voters) {
		this.voters = voters;
	}

	public Integer getAnswersCount() {
		return answersCount;
	}

	public void setAnswersCount(Integer answersCount) {
		this.answersCount = answersCount;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Question) {
			if (((Question) obj).getId().equals(this.getId())) {
				return true;
			}
		}

		return false;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}