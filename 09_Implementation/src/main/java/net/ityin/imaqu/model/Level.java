package net.ityin.imaqu.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the t_title database table.
 * 
 */
@Entity
@Table(name = "T_LEVEL")
public class Level implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private String id;

	@Column(name = "SERIALNAME", length = 30)
	private String serialname;

	/**
	 * if this is the default level of users.
	 */
	@Column(name = "D")
	private byte d;

	@Lob
	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "level")
	private Set<SubLevel> subLevels;

	public Level() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSerialname() {
		return this.serialname;
	}

	public void setSerialname(String serialname) {
		this.serialname = serialname;
	}

	public byte getD() {
		return d;
	}

	public void setD(byte d) {
		this.d = d;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SubLevel> getSubLevels() {
		return subLevels;
	}

	public void setSubLevels(Set<SubLevel> subLevels) {
		this.subLevels = subLevels;
	}
}