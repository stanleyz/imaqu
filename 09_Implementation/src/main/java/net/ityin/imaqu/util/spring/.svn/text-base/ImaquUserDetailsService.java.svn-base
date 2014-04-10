/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.spring;

import java.util.HashSet;
import java.util.Set;

import net.ityin.imaqu.dao.UserDao;
import net.ityin.imaqu.model.proxy.UserProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Component
public class ImaquUserDetailsService implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserProxy user = new UserProxy(userDao.findByNickname(username));

		if (user.getUser() == null) {
			String message = "There is no such user: " + username;
			logger.debug(message);
			throw new UsernameNotFoundException(message);
		}

		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		dbAuthsSet.add(new GrantedAuthorityImpl(user.getUser().getRole()
				.getName()));

		user.setAuthorities(dbAuthsSet);
		return user;
	}
}
