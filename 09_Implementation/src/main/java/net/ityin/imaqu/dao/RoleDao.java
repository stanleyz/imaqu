/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao;

import net.ityin.imaqu.model.Role;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface RoleDao extends GenericDao<Role, String> {
	public Role findByName(String name);
}
