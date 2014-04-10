/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao.hibernate;

import java.util.List;

import net.ityin.imaqu.dao.RoleDao;
import net.ityin.imaqu.model.Role;

import org.springframework.stereotype.Repository;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@SuppressWarnings("unchecked")
@Repository(value = "roleDao")
public class RoleDaoHibernate extends GenericDaoHibernate<Role, String>
		implements RoleDao {

	public RoleDaoHibernate() {
		super(Role.class);
	}

	public Role findByName(String name) {
		Object[] params = new Object[] { name };
		List<Role> roles = this.getHibernateTemplate().findByNamedQuery(
				"findRoleByName", params);
		if (roles.size() != 0) {
			return roles.get(0);
		} else {
			return null;
		}
	}
}
