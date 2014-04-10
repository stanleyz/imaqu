package net.ityin.imaqu.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

/**
 * The persistent class for the T_CATEGORY database table.
 * 
 */
@Entity
@Table(name = "T_CATEGORY")
@NamedQueries({
		@NamedQuery(name = "getChildrenById", query = "from Category c where c.parentId=?"),
		@NamedQuery(name = "getDCategory", query = "from Category c where c.d=1") })
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private String id;

	@Lob()
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NAME", nullable = false, length = 60)
	private String name;

	@OneToMany(mappedBy = "category")
	private Set<Question> questions;

	@Column(name = "PARENT_ID", nullable = false)
	private String parentId;

	@Column(name = "ANCESTORS_ID")
	private String ancestorsId;

	/**
	 * if this is the default category for questions.
	 */
	@Column(name = "D")
	private byte d;

	@Formula("(select count(*) from T_QUESTION q where q.CATEGORY_ID=id)")
	private int questionsCount;

	public Category() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> Questions) {
		this.questions = Questions;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public byte getD() {
		return d;
	}

	public void setD(byte d) {
		this.d = d;
	}

	public int getQuestionsCount() {
		return questionsCount;
	}

	public void setQuestionsCount(int questionsCount) {
		this.questionsCount = questionsCount;
	}

	public String getAncestorsId() {
		return ancestorsId;
	}

	public void setAncestorsId(String ancestorsId) {
		this.ancestorsId = ancestorsId;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Category) {
			if (((Category) obj).getId().equals(this.getId())) {
				return true;
			}
		}

		return false;
	}

}