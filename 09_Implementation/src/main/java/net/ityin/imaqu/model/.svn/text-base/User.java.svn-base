package net.ityin.imaqu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

/**
 * The persistent class for the t_user database table.
 * 
 */
@Entity()
@Table(name = "T_USER")
@NamedQueries({ @NamedQuery(name = "findUserByNickname", query = "from User u where u.nickname=?") })
public class User implements Serializable {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDAT")
	private Date createdAt;

	@Column(name = "EMAIL", nullable = false, length = 50)
	private String email;

	@Column(name = "LASTIP", length = 7)
	private String lastIP;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTLOGIN")
	private Date lastLogin;

	@Column(name = "NICKNAME", nullable = false, length = 60)
	private String nickname;

	@Column(name = "PASSWORD", nullable = false, length = 32)
	private String password;

	@Column(name = "SCORE")
	private int score;

	@Column(name = "LEVEL_ID", length = 30)
	private String levelId;

	@Formula("(select sl.NAME from T_SUBLEVEL sl, T_LEVEL l, T_USER u where u.NICKNAME=nickname and (u.LEVEL_ID=l.ID or l.ID=(select tl.ID from T_LEVEL tl where tl.D=1 limit 1)) and l.ID=sl.LEVEL_ID and u.SCORE>=sl.MINSCORE and u.SCORE<=sl.MAXSCORE limit 1)")
	private String levelName;

	/**
	 * This is the status of the this question.<br />
	 * <br />
	 * 0 represents female; <br />
	 * 1 stands for male; <br />
	 */
	@Column(name = "GENDER")
	private byte gender;

	@Column(name = "PASSHASH")
	private String passHash;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false)
	private Role role;

	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "user")
	private Set<Question> questions;

	@Formula("(select count(*) from T_QUESTION q where q.USER_ID=id)")
	private int questionsCount;

	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "user")
	private Set<Answer> answers;

	@Formula("(select count(*) from T_ANSWER a where a.USER_ID=id)")
	private int answersCount;

	@ManyToMany(cascade = { CascadeType.REMOVE }, mappedBy = "voters")
	private Set<Question> votings;

	public User() {
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

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastIP() {
		return this.lastIP;
	}

	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role Role) {
		this.role = Role;
	}

	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> Questions) {
		this.questions = Questions;
	}

	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> Answers) {
		this.answers = Answers;
	}

	public Set<Question> getVotings() {
		return votings;
	}

	public void setVotings(Set<Question> votings) {
		this.votings = votings;
	}

	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			if (((User) obj).getId().equals(this.getId())
					|| ((User) obj).getNickname().equals(this.getNickname())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	public int getQuestionsCount() {
		return questionsCount;
	}

	public int getAnswersCount() {
		return answersCount;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}
}