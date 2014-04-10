/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service.impl;

import net.ityin.imaqu.dao.RoleDao;
import net.ityin.imaqu.model.Role;
import net.ityin.imaqu.service.RoleManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Service(value = "roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, String> implements
		RoleManager {
	private RoleDao roleDao;

	@Autowired
	public RoleManagerImpl(RoleDao roleDao) {
		super(roleDao);
		this.roleDao = roleDao;
	}

	public Role getGeneralRole() {
		return roleDao.findByName(GENERAL_ROLE);
	}

	public Role getRoleAdmin() {
		return roleDao.findByName(ROLE_ADMIN);
	}
}
