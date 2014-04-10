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

/**
 * The persistent class for the T_ROLE database table.
 * 
 */
@Entity
@Table(name = "T_ROLE")
@NamedQueries(@NamedQuery(name = "findRoleByName", query = "from Role r where r.name=?"))
public class Role implements Serializable {
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

	@OneToMany(mappedBy = "role")
	private Set<User> Users;

	public Role() {
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

	public Set<User> getUsers() {
		return this.Users;
	}

	public void setUsers(Set<User> Users) {
		this.Users = Users;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Role) {
			if (((Role) obj).getId().equals(this.getId())) {
				return true;
			}
		}

		return false;
	}

}