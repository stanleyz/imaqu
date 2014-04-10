/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service;

import net.ityin.imaqu.model.Role;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface RoleManager extends GenericManager<Role, String> {
	public static final String GENERAL_ROLE = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	/**
	 * This is used to find the common role used by this system.<br/>
	 * 
	 * Common role is the role that normal users belongs to.
	 * 
	 * @return General role.
	 */
	public Role getGeneralRole();

	/**
	 * This is used to get the role admin. <br />
	 * 
	 * Role admin is the role which rules everthing of this system.
	 * 
	 * @return Role admin.
	 */
	public Role getRoleAdmin();
}