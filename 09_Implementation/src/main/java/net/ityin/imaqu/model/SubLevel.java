package net.ityin.imaqu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the t_title database table.
 * 
 */
@Entity
@Table(name = "T_SUBLEVEL")
public class SubLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private String id;

	@Column(name = "MAXSCORE")
	private int maxScore;

	@Column(name = "MINSCORE")
	private int minScore;

	@Column(name = "NAME", nullable = false, length = 60)
	private String name;

	@ManyToOne()
	@JoinColumn(name = "LEVEL_ID")
	private Level level;

	public SubLevel() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public int getMinScore() {
		return this.minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}